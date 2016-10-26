package dbutil.queryconfig.jsqlparser.statement.create.table;

import java.util.List;

import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;


public class ColDataType {

    private String dataType;

    private List<String> argumentsStringList;

    public List<String> getArgumentsStringList() {
        return argumentsStringList;
    }

    public String getDataType() {
        return dataType;
    }

    public void setArgumentsStringList(List<String> list) {
        argumentsStringList = list;
    }

    public void setDataType(String string) {
        dataType = string;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(dataType);
    	if(argumentsStringList!=null && argumentsStringList.size()>0){
    		PlainSelect.getStringList(sb,argumentsStringList, ",", true) ;
    	}
        return sb.toString();
    }
}
