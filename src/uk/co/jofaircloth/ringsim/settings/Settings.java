package uk.co.jofaircloth.ringsim.settings;

import uk.co.jofaircloth.ringsim.enums.Libraries;

public abstract class Settings {
	
	private static String MSLibFileName;
	private static String pdfFileLocation;
	private static boolean isTitleFileName = false;
	
	public static void setMSLibFileName(String fileName) {
		MSLibFileName = fileName;
	}
	public static String getMSLibFileName() {
		if (MSLibFileName == null) {
			return Libraries.MSLIB.toString();
		} else {
			return MSLibFileName;
		}
	}
	
	public static void setPdfFileLocation(String pdfLocation) {
		pdfFileLocation = pdfLocation;
	}
	public static String getPdfFileLocation() {
		if (pdfFileLocation == null) {
			return "C:/Users/gibbs/Documents/Me/Code/Ringsim/";
		} else {
			return pdfFileLocation;
		}
	}
	public static boolean getIsTitleFileName() {
		return isTitleFileName;
	}
	public static void setIsTitleFileName(boolean isTitle) {
		isTitleFileName = isTitle;
	}
}
