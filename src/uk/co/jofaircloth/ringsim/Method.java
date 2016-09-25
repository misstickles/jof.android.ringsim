/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim;

import java.util.ArrayList;
import uk.co.jofaircloth.ringsim.enums.*;

/**
 *
 * @author gibbs
 * bool isSymmetrical(List<Rows> method) // checks it's symmetrical, excl half lead and lead end
 */
public class Method extends ArrayList<Row> {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2530727622901602836L;

	public Method() { }
    
    public Method getBlueline(ArrayList<String> notation, MethodStage stage) {
        // this should be a type method
        Method m = new Method();
        Row r1 = new Row(stage.getStageNumber());
        m.add(r1);
        do {
            for (String pn : notation) {
                r1 = new Change(r1, pn).swap();
                // TODO: why did I suddenly have to clone this?? after changing from AL<String> to Method
                m.add((Row)r1.clone());
            }
        } while (!r1.equals(new Row(stage.getStageNumber())));
        
        return m;
    }
    
//    public boolean isSymmetrical(List<Rows> method) {
//        if(length() & 1) return 0;	// Must have an even length
//        for(int i = 0; i < length() / 2 - 1; i++)
//            if((*this)[i] != (*this)[length() - i - 2]) return false;
//                return true;
//    }

    
    
    
    
//    /**
//     * Format the notation string
//        - = .x.
//        also assumes x is not always surrounded by '.'
//        & = repeat the first half backwards
//        + = forwards only
//        NOTE: lead ends and 'external' bells are missing, therefore:
//        If the first place in the row is even, prefix a ‘1’. 
//        For even bell methods, if the last place in the row is odd, add an nths place to the end. 
//        For odd bell methods, if the last place is even, add an nths place to the end.
//        NOTE2: we also have to add the lead end...
//        a-f are 2nds place; g-m are nths place;
//    
//     * @param notation
//     * @param noBells
//     * @param leadHead
//     * @return 
//     */
//    private String formatNotationString(String notation, int noBells, String leadHead) {
//        return "";
//    }
//
//    private ArrayList AddExternalBells(Row change, int noBells) {
//        if (change != null) {
//            // if first place is even, add a 1 (for some reason x mod 2 is 0!!)
//            if (change[0] != 'x' && change[0] != '.' && Convert.ToInt32(change[0]) % 2 == 0)
//                {
//                    change = "1" + change;
//                }
//                // if even and last place is odd - add an nths place to end, or
//                // if odd and last place is even - add an nths place to end
//                if (change[0] != 'x' && change[0] != '.'
//                    && ((noBells % 2 == 0) && (change[change.Length - 1] % 2 == 1))
//                    || ((noBells % 2 == 1) && (change[change.Length - 1] % 2 == 0)))
//                {
//                    return change + noBells.ToString();
//                }
//                else
//                    return change;
//            }
//            else
//                return "";
//
//        return new ArrayList();
//    }
}
