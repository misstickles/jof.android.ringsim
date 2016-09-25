/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.enums;

/**
 *
 * @author gibbs
 */
public enum MethodStage {
    ALL         (0, "Any"),
    SINGLES     (3, "Singles"),
    MINIMUS     (4, "Minimus"),
    DOUBLES     (5, "Doubles"),
    MINOR       (6, "Minor"),
    TRIPLES     (7, "Triples"),
    MAJOR       (8, "Major"),
    CATERS      (9, "Caters"),
    ROYAL       (10, "Royal"),
    CINQUES     (11, "Cinques"),
    MAXIMUS     (12, "Maximus"),
    SEXTUPLES   (13, "Sextuples"),
    FOURTEEN    (14, "Fourteen"),
    SEPTUPLES   (15, "Septuples"),
    SIXTEEN     (16, "Sixteen"),
    OCTUPLES    (17, "Octuples"),
    EIGHTEEN    (18, "Eighteen"),
    NONUPLES    (19, "Nonuples"),
    TWENTY      (20, "Twenty"),
    DECUPLES    (21, "Decuples"),
    TWENTY_TWO  (22, "Twenty-Two");

    private final int stageNumber;
    private final String stageName;
    
    private MethodStage(int number, String name) {
        this.stageNumber = number;
        this.stageName = name;
    }
    
    public int getStageNumber() {
        return stageNumber;
    }
    
    public String getStageName() {
        return stageName;
    }
    
    public static String getStageName(int n) {
    	String stageName = "";
        for (MethodStage m : MethodStage.values()) {
            if (m.getStageNumber() == n) {
                stageName = m.getStageName();
            }
        }
        return stageName;
    }
    
    public static int getStageNumber(String stage) {
    	int stageNo = -1;
        for (MethodStage m : MethodStage.values()) {
            if (m.getStageName().toLowerCase().equals(stage.toLowerCase())) {
                stageNo = m.getStageNumber();
            }
        }
        return stageNo;
    }
    
    public static MethodStage getStage(int n) {
    	MethodStage stage = MethodStage.ALL;
    	for (MethodStage m : MethodStage.values()) {
    		if (m.getStageNumber() == n) {
    			stage = m;
    		}
    	}
    	return stage;
    }
    
    @Override
    public String toString() {
        return stageName;
    }
}

