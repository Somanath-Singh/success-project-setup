package com.aashdit.prod.heads.hims.ipms.utils;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * @author Tapan K.
 * @date 14/09/2018
 */

@SuppressWarnings("static-access")
public class PDFBuilderCommonStylesPcellReturn {

	static PDFBuilderCommonStylesVoidMethods voidClass = new PDFBuilderCommonStylesVoidMethods();

	/**
	 * @since 01-01-2018
	 * @descr Image section in Left-Top corner of PDF
	 * @border No Border
	 * @rowspan Rowspan=3
	 * @REFERS createImageCell, createImageCellRowspan3
	 */
	public static PdfPCell imageCellRowspan3_BorderNone(String strImagePath) throws DocumentException, IOException {
		Image img = Image.getInstance(strImagePath);
		img.scaleAbsolute(240f, 110f);// image width,height
		PdfPCell cell = new PdfPCell(img, true);
		cell.setRowspan(3);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_BASELINE);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 13 - Bold
	 * @align Center
	 * @border None
	 * @REFERS createTitleRowLargeText
	 */
	public static PdfPCell titleRowBoldCenterSize13_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 11 - Bold
	 * @align Center
	 * @border None
	 * @REFERS createTitleRowSmallText
	 */
	public static PdfPCell titleRowBoldCenterSize11_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 10, Bold-Underline
	 * @align Center
	 * @border None
	 * @REFERS createTitleRowSmallTextBoldUnderline
	 */
	public static PdfPCell titleRowBoldUnderlineCenterSize10_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Left
	 * @border LeftTopBottom
	 * @REFERS createCellLabelNormalLeftTopBottomLeftBorderStyle
	 */
	public static PdfPCell pcellBoldLeft_BorderLeftTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftTopBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Right
	 * @border LeftTopBottom
	 * @REFERS createCellLabelRightNormalTopBottomAndLeftBorderStyle
	 */
	public static PdfPCell pcellBoldRight_BorderLeftTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderLeftTopBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Right
	 * @border All
	 * @REFERS createCellLabelNormalRightTopBottomLeftRightBorderStyle
	 */
	public static PdfPCell pcellBoldRight_BorderAll(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderAll(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Left
	 * @border LeftTopBottom
	 * @REFERS createCellLabelNormalLeftTopBottomLeftBorderStyle
	 */
	public static PdfPCell pcellNormalLeft_BorderLeftTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftTopBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Right
	 * @border LeftTopBottom
	 * @REFERS createCellLabelRightNormalTopBottomAndLeftBorderStyle
	 */
	public static PdfPCell pcellNormalRight_BorderLeftTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderLeftTopBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Right
	 * @border All
	 * @REFERS createCellLabelNormalRightTopBottomLeftRightBorderStyle
	 */
	public static PdfPCell pcellNormalRight_BorderAll(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderAll(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal-Italic
	 * @align Right
	 * @border None
	 * @REFERS createCellLabelItalicRightStyle
	 */
	public static PdfPCell pcellNormalRightItalic_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Right
	 * @border None
	 * @REFERS createCellLabelNormalCenter
	 */
	public static PdfPCell pcellNormalRight_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Left
	 * @border None
	 * @REFERS createCellLabelNormalLeftStyle
	 */
	public static PdfPCell pcellNormalLeft_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Right
	 * @border Bottom
	 * @REFERS createCellLabelNormalRightBottomBorderStyle
	 */
	public static PdfPCell pcellNormalRight_BorderBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Left
	 * @border Bottom
	 * @REFERS createCellLabelNormalLeftBottomBorderStyle
	 */
	public static PdfPCell pcellBoldLeft_BorderBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_LEFT); // Center vertically
		cell.setBorderWidth(1); // Apply border width on all sides
		cell.setPadding(5);
//		voidClass.voidLeft_BorderBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Right
	 * @border Bottom
	 * @REFERS createCellLabelNormalRightBottomBorderStyle
	 */
	public static PdfPCell pcellBoldRight_BorderBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_LEFT); // Center vertically
		cell.setBorderWidth(1); // Apply border width on all sides
//		voidClass.voidRight_BorderBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Center
	 * @border None
	 * @REFERS createCellLabelNormalCenter
	 */
	public static PdfPCell pcellNormalCenter_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Left
	 * @border Bottom
	 * @REFERS createCellLabelNormalLeftBottomBorderStyle
	 */
	public static PdfPCell pcellNormalLeft_BorderBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal, LeftPad=3, RightPad=5
	 * @align Left
	 * @border None
	 * @REFERS createCellLabelNormalLeftHighPaddingStyle
	 */
	public static PdfPCell pcellNormalLeft_BorderNone_Padding3_5(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold, LeftPad=3, RightPad=5
	 * @align Left
	 * @border None
	 * @REFERS createCellLabelBoldLeftHighPaddingStyle
	 */
	public static PdfPCell pcellBoldLeft_BorderNone_Padding3_5(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 8, Normal-Italic
	 * @align Right
	 * @border None
	 * @REFERS createCellLabelItalicRight8HighPaddingStyle
	 */
	public static PdfPCell pcellNormalRightItalicSize8_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal-Italic
	 * @align Left
	 * @border None
	 * @REFERS
	 */
	public static PdfPCell pcellNormalItalicLeft_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLDITALIC, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 10 - Bold-Red
	 * @align Center
	 * @border None
	 * @REFERS createNoDataRowTextBoldCenterRed
	 */
	public static PdfPCell noDataRowBoldCenterRedSize10_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.RED);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Right
	 * @border TopBottom
	 * @REFERS createRowTopBottomBorderNormalRightStyle
	 */
	public static PdfPCell pcellBoldRight_BorderTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderTopBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Center
	 * @border None
	 * @REFERS createCellLabelNormalCenter
	 */
	public static PdfPCell pcellBoldCenter_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold-Italic
	 * @align Left
	 * @border None
	 * @REFERS createCellLabelBoldItalicLeftStyle
	 */
	public static PdfPCell pcellBoldItalicLeft_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLDITALIC, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderNone(cell);
		return cell;
	}

	public static PdfPCell pcellNormalLeft_BorderDottedBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderDottedBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalRight_BorderDottedBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderDottedBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldLeft_BorderTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderTopBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldRight_BorderRightTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderRightTopBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalLeft_BorderLeftTop(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftTop(cell);
		return cell;
	}

	public static PdfPCell pcellNormalLeft_BorderTop(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderTop(cell);
		return cell;
	}

	public static PdfPCell pcellNormalLeft_BorderLeft(String pcellText, Float fltFontSize) {
		Font font = new Font(FontFamily.HELVETICA, fltFontSize, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeft(cell);
		return cell;
	}

	public static PdfPCell pcellNormalRight_BorderTop(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderTop(cell);
		return cell;
	}

	public static PdfPCell pcellNormalRight_BorderRightTop(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderRightTop(cell);
		return cell;
	}
	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Left
	 * @border TopBottom
	 * @REFERS createCellLabelLeftNormalTopAndBottomBorders
	 */
//	public static PdfPCell pcellBoldLeft_BorderTopBottom(String pcellText)
//	{
//		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
//		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
//		voidClass.voidLeft_BorderTopBottom(cell);
//		return cell;
//	}
//	
//	/**
//	 * @since	01-01-2018
//	 * @font	9, Bold
//	 * @align	Right
//	 * @border	RightTopBottom
//	 * @REFERS	createCellLabelRightNormalTopBottomAndRightBorderStyle
//	 */
//	public static PdfPCell pcellBoldRight_BorderRightTopBottom(String pcellText)
//	{
//		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
//		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
//		voidClass.voidRight_BorderRightTopBottom(cell);
//		return cell;
//	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Left
	 * @border LeftBottom
	 * @REFERS createCellLabelNormalLeftBottomLeftBorderStyle
	 */
	public static PdfPCell pcellNormalLeft_BorderLeftBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Left
	 * @border None
	 * @REFERS createCellLabelNormalLeftStyle
	 */
	public static PdfPCell pcellBoldLeft_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal-Italic, RED
	 * @align Left
	 * @border None
	 * @REFERS
	 */
	public static PdfPCell pcellNormalItalicLeftRED_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLDITALIC, BaseColor.RED);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderNone(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Normal
	 * @align Right
	 * @border RightBottom
	 * @REFERS pcellNormalRight_BorderRightBottom
	 */
	public static PdfPCell pcellNormalRight_BorderRightBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderRightBottom(cell);
		return cell;
	}

	public static PdfPCell titleRowBoldCenterSize8_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	public static PdfPCell titleRowBoldCenterSize9_BorderNone(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderNone(cell);
		return cell;
	}

	public static PdfPCell imageCellRowspan2_BorderNone(String strImagePath) throws DocumentException, IOException {
		Image img = Image.getInstance(strImagePath);
		img.scaleAbsolute(240f, 110f);// image width,height
		PdfPCell cell = new PdfPCell(img, true);
		cell.setPaddingLeft(3f);
		cell.setPaddingTop(3f);
		cell.setRowspan(2);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_BASELINE);
		return cell;
	}

	public static PdfPCell pcellBoldLeft_BorderAll(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderAll(cell);
		return cell;
	}

	public static PdfPCell pcellBoldLeft_BorderLeftBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftBottom(cell);
		return cell;
	}

	/**
	 * @since 01-01-2018
	 * @font 9, Bold
	 * @align Center
	 * @border LeftBottom
	 * @REFERS createCellLabelNormalCenterAlignLeftBottomBorderStyle
	 */
	public static PdfPCell pcellBoldCenter_BorderLeftBottom(String pcellText, Float fltFontSize) {
		Font font = new Font(FontFamily.HELVETICA, fltFontSize, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderLeftBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldLeft_BorderLeft(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeft(cell);
		return cell;
	}

	public static PdfPCell pcellBoldLeft_BorderLeftRightBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftRightBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalLeft_BorderLeftRightBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderLeftRightBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldCenter_BorderLeftTopBottom(String pcellText, Float fltFontSize) {
		Font font = new Font(FontFamily.HELVETICA, fltFontSize, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderLeftTopBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalRight_BorderTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderTopBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalRight_BorderLeftRightBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderLeftRightBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldRight_BorderLeftRightBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderLeftRightBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldRight_BorderLeftBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderLeftBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalCenter_BorderLeftBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderLeftBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalCenter_BorderLeftRightBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderLeftRightBottom(cell);
		return cell;
	}

	public static PdfPCell pcellNormalRight_BorderLeftBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidRight_BorderLeftBottom(cell);
		return cell;
	}

	public static PdfPCell pcellBoldCenter_BorderAll(String pcellText, Float fltFontSize) {
		Font font = new Font(FontFamily.HELVETICA, fltFontSize, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidCenter_BorderAll(cell);
		return cell;
	}

	public PdfPCell pcellBoldLeft_BorderRightTopBottom(String pcellText) {
		Font font = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(pcellText, font));
		voidClass.voidLeft_BorderRightTopBottom(cell);
		return cell;
	}

	public static PdfPCell pcellRight_BorderBottom(String content) {
		PdfPCell cell = new PdfPCell(new Phrase(content));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align text to the right
		cell.setBorderWidthBottom(1); // Add a bottom border
		cell.setPadding(5); // Add padding for better visual spacing
		return cell;
	}

	public static PdfPCell pcellLeft_BorderBottom(String content) {
		PdfPCell cell = new PdfPCell(new Phrase(content));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setBorderWidthBottom(1); // Add a bottom border
		cell.setBorderWidthTop(0); // No top border
		cell.setBorderWidthLeft(0); // No left border
		cell.setBorderWidthRight(0); // No right border
		cell.setPadding(5); // Add padding for better visual spacing
		return cell;
	}

}
