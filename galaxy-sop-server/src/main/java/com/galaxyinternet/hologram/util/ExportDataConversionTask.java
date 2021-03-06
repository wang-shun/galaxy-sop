package com.galaxyinternet.hologram.util;

import com.galaxyinternet.common.utils.SpringContextManager;
import com.galaxyinternet.hologram.service.CacheOperationServiceImpl;
import com.galaxyinternet.service.hologram.ReportExportService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;


public class ExportDataConversionTask extends RecursiveTask<Map<String,Object>>{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ExportDataConversionTask.class);

    private ReportExportService reportExportService = SpringContextManager.getBean(ReportExportService.class);

    private String tempfilePath;
    private String currentMark;
    private String preCode;
    private Long proId;
    private Set<Long> ids;
    private String idType;
    private Map<Long, String> valueIdNameMap;

    public ExportDataConversionTask(){}
    public ExportDataConversionTask(String preCode, Long proId,Set<Long> ids,String idType,Map<Long, String> valueIdNameMap,String currentMark,String tempfilePath){
        super();
        this.tempfilePath = tempfilePath;
        this.currentMark = currentMark;
        this.preCode = preCode;
        this.proId = proId;
        this.ids = ids;
        this.idType = idType;
        this.valueIdNameMap = valueIdNameMap;
    }



    @Override
    protected Map<String,Object> compute() {
        Map<String, Object> map = new HashMap<>();

        if(null == idType){
            //boolean isScore = false;
            Map<String, Set<Long>> titletype_titleIds = null;
            if(StringUtils.isNotBlank(preCode) && (preCode.startsWith("EN") ||preCode.startsWith("CN"))){
                //isScore = true;
                titletype_titleIds = CacheOperationServiceImpl.code_titletype_titleIds_forAllgrade.get(preCode);
            }else {
                titletype_titleIds = CacheOperationServiceImpl.code_titletype_titleIds.get(preCode);
            }

            Set<Long> project_ids = titletype_titleIds.get("project");
            Set<Long> result_ids = titletype_titleIds.get("result");
            Set<Long> listdata_ids = titletype_titleIds.get("listdata");
            Set<Long> fixedtable_ids = titletype_titleIds.get("fixedtable");
            Set<Long> file_ids = titletype_titleIds.get("file");
            //Set<Long> resultGrage_ids = titletype_titleIds.get("resultGrage");

            List<ExportDataConversionTask> subTasks = new ArrayList<>();
            subTasks.add(new ExportDataConversionTask(preCode,proId,project_ids,"project",valueIdNameMap,null,null));
            subTasks.add(new ExportDataConversionTask(preCode,proId,result_ids,"result",valueIdNameMap,null,null));
            subTasks.add(new ExportDataConversionTask(preCode,proId,listdata_ids,"listdata",valueIdNameMap,null,null));
            subTasks.add(new ExportDataConversionTask(preCode,proId,fixedtable_ids,"fixedtable",valueIdNameMap,null,null));
            subTasks.add(new ExportDataConversionTask(preCode,proId,file_ids,"file",valueIdNameMap,currentMark,tempfilePath));
            /*if(isScore){
                subTasks.add(new ExportDataConversionTask(preCode,proId,null,"score",null,null,null));
            }*/
            invokeAll(subTasks);

            for(ExportDataConversionTask tem : subTasks){
                map.putAll(tem.join());
            }
        }else{
            try {
                switch (idType){
                    case "project" :
                        map = reportExportService.projectTitleResult(ids,proId,valueIdNameMap);
                        break;
                    case "result" :
                        map = reportExportService.resultTitleResult(ids,proId,valueIdNameMap,preCode);
                        break;
                    case "listdata" :
                        map = reportExportService.listdataTitleResult(ids,proId,valueIdNameMap);
                        break;
                    case "fixedtable" :
                        map = reportExportService.fixedtableTitleResult(ids,proId,valueIdNameMap);
                        break;
                    case "file" :
                        map = reportExportService.fileTitleResult(ids, proId, currentMark, tempfilePath, preCode);
                        /*if("NO".equals(preCode)){
                            map = reportExportService.fileTitleResult(ids,proId);  //doc
                        }else {
                            map = reportExportService.fileTitleResult(ids, proId, currentMark, tempfilePath, preCode);
                        }*/

                        break;
                    /*case "score" :
                        map = reportExportService.titleScoreResult(proId,preCode);
                        break;*/

                    default :
                        break;
                }
            } catch (Exception e) {
                logger.error("ProgressRecursiveTask compute",e);
            }
        }

        return map;
    }


}
