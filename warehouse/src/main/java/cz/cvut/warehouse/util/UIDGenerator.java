package cz.cvut.warehouse.util;

public class UIDGenerator {

	 private static final String ALPHA_NUM ="0123456789abcdef";
	    private static final Integer UID_Length = 8;
	 
	    public static String getRandomUID(){
	        return getAlphaNumeric(UID_Length);
	    }
	    
	    protected static String getAlphaNumeric(int len) {
	        StringBuilder sb = new StringBuilder(len);
	        for (int i=0; i<len; i++) {
	            int ndx = (int)(Math.random()*ALPHA_NUM.length());
	            sb.append(ALPHA_NUM.charAt(ndx));
	        }
	        return sb.toString();
	    }
	    
}
