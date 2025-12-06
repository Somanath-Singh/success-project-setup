package com.aashdit.setup.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * @author Tapan K.
 * @date 14/09/2018
 */

public class PDFBuilderCommonStylesVoidMethods {
	/*
	 * --------------------------------------- MISCELLANEOUS
	 * --------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left, PaddingTop=14, Padding-Bottom=17
	 * @border Left-Bottom
	 * @return VOID
	 * @refer
	 */
	public static void voidLeft_BorderLeftBottom_Padding14_17(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(14f);
		pCell.setPaddingBottom(17f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Left, PaddingTop=14, Padding-Bottom=17
	 * @border Right-Bottom
	 * @return VOID
	 * @refer
	 */
	public static void voidLeft_BorderRightBottom_Padding14_17(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(14f);
		pCell.setPaddingBottom(17f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Left, PaddingTop=14, Padding-Bottom=17
	 * @border Right-Bottom
	 * @return VOID
	 * @refer
	 */
	public static void voidLeft_BorderLeftRightBottom_Padding14_17(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(14f);
		pCell.setPaddingBottom(17f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right, PaddingTop=14, Padding-Bottom=17
	 * @border Right-Bottom
	 * @return VOID
	 * @refer
	 */
	public static void voidRight_BorderRightBottom_Padding14_17(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(14f);
		pCell.setPaddingBottom(17f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, PaddingTop=20, Padding-Bottom=5
	 * @border None
	 * @return VOID
	 * @refer cellLabelBoldCenter, emptyCellStyleHighPadding25Top
	 */
	public static void voidCenter_BorderNone_Padding20_5(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setPaddingTop(20f);
		pCell.setPaddingBottom(5f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, PaddingTop=30, Padding-Bottom=5
	 * @border None
	 * @return VOID
	 */
	public static void voidCenter_BorderNone_Padding30_5(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setPaddingTop(30f);
		pCell.setPaddingBottom(5f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
	}

	/**
	 * @since 01-01-2018
	 * @align Left, PaddingTop=14, Padding-Bottom=17
	 * @border Left-Bottom
	 * @return VOID
	 * @refer emptyCellStyle
	 */
	public static void voidEmptyCell_BorderNone(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(2f);
		pCell.setPaddingBottom(5f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Bottom
	 * @return VOID
	 * @refer cellLabelLeftNoPaddingStyle
	 */
	public static void voidRight_BorderNone_PaddingNone(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBorder(0);
	}

	/*
	 * --------------------------------------- MISCELLANEOUS
	 * --------------------------------------------
	 */

	/*
	 * --------------------------------------- NO BORDER
	 * --------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border None
	 * @return VOID
	 * @refer cellLabelLeftStyle, cellLabelLeftAlignNoBorderStyle,
	 *        cellLabelLeftLowPaddingStyle, NoBorderNormalLeftStyle
	 */
	public static void voidLeft_BorderNone(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Left, Padding-Top=10
	 * @border None
	 * @return VOID
	 */
	public static void voidLeft_BorderNone_Padding10_4(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(10f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border None
	 * @return VOID
	 * @refer cellLabelNormalCenter
	 */
	public static void voidCenter_BorderNone(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, Padding=0
	 * @border None
	 * @return VOID
	 */
	public static void voidCenter_BorderNone_Padding0(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(0);
		pCell.setPaddingBottom(0);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, PaddingTop=10
	 * @border None
	 * @return VOID
	 * @refer cellLabelNormalCenter
	 */
	public static void voidCenter_BorderNone_Padding10(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(10f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border None
	 * @return VOID
	 * @refer cellLabelRightStyle
	 */
	public static void voidRight_BorderNone(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Bottom
	 * @return VOID
	 * @refer cellLabelLeftAlignBottomBorderStyle, dataRowHeaderBoldLeft
	 */
	public static void voidRight_BorderDottedBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(PdfPCell.NO_BORDER);
		pCell.setCellEvent(new TableDottedBorderEvent(PdfPCell.BOTTOM));
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Right, PaddingTop=1, PaddingBottom=10
	 * @border None
	 * @return VOID
	 * @refer
	 */
	public static void voidRight_BorderNone_Padding10_4(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(10f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/*
	 * ------------------------------------------- NO BORDER
	 * ----------------------------------------
	 */

	/*
	 * ------------------------------------------- ALL BORDER
	 * ----------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border All
	 * @return VOID
	 */
	public static void voidLeft_BorderAll(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border All
	 * @return VOID
	 * @refer headerRowWithAllBorder
	 */
	public static void voidCenter_BorderAll(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidCenterPINKBG_BorderAll(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.PINK);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, Colspan=2
	 * @border All
	 * @return VOID
	 * @refer createBoldCenterAlignLeftRightTopBottomBorderColspan2
	 */
	public static void voidCenter_BorderAll_Colspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setColspan(2);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, Colspan=2
	 * @border Left-Top-Bottom
	 * @return VOID
	 * @refer createBoldCenterAlignLeftTopBottomBorderColspan2
	 */
	public static void voidCenter_BorderLeftTopBottom_Colspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setColspan(2);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, Rowspan=2
	 * @border Left-Top-Bottom
	 * @return VOID
	 * @refer createBoldCenterAlignLeftTopBottomBorderRowspan2
	 */
	public static void voidCenter_BorderLeftTopBottom_Rowspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setRowspan(2);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, Rowspan=2
	 * @border Left-Right-Bottom
	 * @return VOID
	 * @refer createBoldCenterAlignLeftRightBottomBorderRowspan2
	 */
	public static void voidCenter_BorderLeftRightBottom_Rowspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setRowspan(2);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center, Rowspan=2
	 * @border All
	 * @return VOID
	 * @refer createBoldCenterAlignLeftRightTopBottomBorderRowspan2
	 */
	public static void voidCenter_BorderAll_Rowspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setRowspan(2);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border All
	 * @return VOID
	 * @refer cellLabelRightAlignTopBottomLeftRightBorderStyle
	 */
	public static void voidRight_BorderAll(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * ------------------------------------------ ALL BORDER
	 * -----------------------------------------
	 */

	/*
	 * ------------------------------------------ LEFT BORDER
	 * -----------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left
	 * @return VOID
	 * @refer cellLabelLeftAlignLeftBorderStyle
	 */
	public static void voidLeft_BorderLeft(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left
	 * @return VOID
	 */
	public static void voidCenter_BorderLeft(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left
	 * @return VOID
	 * @refer cellLabelNormalRightAlignLeftBorderStyle
	 */
	public static void voidRight_BorderLeft(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
	}
	/*
	 * ---------------------------------------- LEFT BORDER
	 * -------------------------------------------
	 */

	/*
	 * ---------------------------------------- RIGHT BORDER
	 * -------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Right
	 * @return VOID
	 * @refer cellLabelLeftAlignRightBorderStyle, cellLabelLeftAlignRightBorderStyle
	 */
	public static void voidLeft_BorderRight(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Right
	 * @return VOID
	 */
	public static void voidCenter_BorderRight(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Right
	 * @return VOID
	 * @refer cellLabelRightBorderRightStyle
	 */
	public static void voidRight_BorderRight(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
	}
	/*
	 * ------------------------------------------- RIGHT BORDER
	 * ----------------------------------------
	 */

	/*
	 * ------------------------------------------- TOP BORDER
	 * ----------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Top
	 * @return VOID
	 * @refer cellLabelLeftAlignTopBorderStyle
	 */
	public static void voidLeft_BorderTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Top
	 * @return VOID
	 */
	public static void voidCenter_BorderTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Top
	 * @return VOID
	 */
	public static void voidRight_BorderTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthTop(0.5f);
	}
	/*
	 * ---------------------------------------- TOP BORDER
	 * -------------------------------------------
	 */

	/*
	 * ---------------------------------------- BOTTOM BORDER
	 * -------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Bottom
	 * @return VOID
	 * @refer cellLabelLeftAlignBottomBorderStyle, dataRowHeaderBoldLeft
	 */
	public static void voidLeft_BorderBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Bottom
	 * @return VOID
	 * @refer cellLabelLeftAlignBottomBorderStyle, dataRowHeaderBoldLeft
	 */
	public static void voidLeft_BorderDottedBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(PdfPCell.NO_BORDER);
		pCell.setCellEvent(new TableDottedBorderEvent(PdfPCell.BOTTOM));
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	public static void voidLeft_BorderDottedTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(PdfPCell.NO_BORDER);
		pCell.setCellEvent(new TableDottedBorderEvent(PdfPCell.TOP));
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Bottom 1f
	 * @return VOID
	 * @REFERS sectionHeaderBoldLeftThickBottomLineStyle
	 */
	public static void voidLeft_BorderBottom1f_Padding7_4(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(7f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidLeft_BorderBottom1f_Padding7_4_no_bottom_width(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(7f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Bottom
	 * @return VOID
	 * @REFERS sectionHeaderBoldCenterStyle
	 */
	public static void voidCenter_BorderBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Bottom 1f
	 * @return VOID
	 * @REFERS sectionHeaderBoldCenterThickBottomLineStyle
	 */
	public static void voidCenter_BorderBottom1f_Padding7_4(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(7f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidCenter_BorderBottom1f_Padding7_4_no_bottom_width(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(7f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Bottom
	 * @return VOID
	 * @refer cellLabelRightAlignBottomBorderStyle, dataRowHeaderBoldRight,
	 *        sectionHeaderBoldRightStyle
	 */
	public static void voidRight_BorderBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Bottom 1f
	 * @return VOID
	 * @REFERS sectionHeaderBoldRightThickBottomLineStyle
	 */
	public static void voidRight_BorderBottom1f_Padding7_4(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(7f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidRight_BorderBottom1f_Padding7_4_no_bottom_width(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(7f);
		pCell.setBackgroundColor(BaseColor.WHITE);
	}

	/*
	 * -------------------------------------- BOTTOM BORDER
	 * ---------------------------------------------
	 */

	/*
	 * -------------------------------------- LEFT-RIGHT BORDER
	 * ---------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left-Right
	 * @return VOID
	 * @refer cellLabelLeftAlignLeftRightBorderStyle
	 */
	public static void voidLeft_BorderLeftRight(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Right
	 * @return VOID
	 */
	public static void voidCenter_BorderLeftRight(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left-Right
	 * @return VOID
	 * @refer cellLabelNormalRightAlignLeftRightBorderStyle
	 */
	public static void voidRight_BorderLeftRight(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
	}
	/*
	 * ------------------------------------- LEFT-RIGHT BORDER
	 * ----------------------------------------------
	 */

	/*
	 * ------------------------------------- LEFT-TOP BORDER
	 * ----------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left-Top
	 * @return VOID
	 */
	public static void voidLeft_BorderLeftTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Top
	 * @return VOID
	 */
	public static void voidCenter_BorderLeftTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left-Top
	 * @return VOID
	 */
	public static void voidRight_BorderLeftTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}
	/*
	 * ------------------------------------- LEFT-TOP BORDER
	 * ----------------------------------------------
	 */

	/*
	 * ------------------------------------- LEFT-BOTTOM BORDER
	 * ----------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left-Bottom
	 * @return VOID
	 * @refer cellLabelLeftAlignBottomLeftBorderStyle
	 */
	public static void voidLeft_BorderLeftBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidLeft_BorderLeftBottomLightGrayBG(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Bottom
	 * @return VOID
	 * @refer cellLabelCenterAlignLeftBottomBorderStyle
	 */
	public static void voidCenter_BorderLeftBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidCenterPINKBG_BorderLeftBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.PINK);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left-Bottom
	 * @return VOID
	 * @refer cellLabelRightBorderBottomAndLeftStyle
	 */
	public static void voidRight_BorderLeftBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidRight_BorderLeftBottomGrayBG(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidRight_BorderBottomGrayBG(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * --------------------------------------- LEFT-BOTTOM BORDER
	 * --------------------------------------------
	 */

	/*
	 * --------------------------------------- TOP-BOTTOM BORDER
	 * --------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Top-Bottom
	 * @return VOID
	 * @refer cellLabelLeftAlignTopBottomBorderStyle
	 */
	public static void voidLeft_BorderTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Top-Bottom
	 * @return VOID
	 * @refer headerGSTRDetailsRowWithBorder
	 */
	public static void voidCenter_BorderTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Top-Bottom
	 * @return VOID
	 * @refer topBottomBorderNormalRightStyle
	 */
	public static void voidRight_BorderTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * --------------------------------------- TOP-BOTTOM BORDER
	 * --------------------------------------------
	 */

	/*
	 * --------------------------------------- RIGHT-TOP BORDER
	 * --------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Right-Top
	 * @return VOID
	 */
	public static void voidLeft_BorderRightTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Right-Top
	 * @return VOID
	 */
	public static void voidCenter_BorderRightTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Right-Top
	 * @return VOID
	 */
	public static void voidRight_BorderRightTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}
	/*
	 * --------------------------------------- RIGHT-TOP BORDER
	 * --------------------------------------------
	 */

	/*
	 * --------------------------------------- RIGHT-BOTTOM BORDER
	 * --------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Right-Bottom
	 * @return VOID
	 * @refer cellLabelCenterAlignRightBottomBorderStyle
	 */
	public static void voidLeft_BorderRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidLeft_BorderRightBottomLightGrayBG(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Right-Bottom
	 * @return VOID
	 */
	public static void voidCenter_BorderRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Right-Bottom
	 * @return VOID
	 * @refer cellLabelRightBorderBottomAndRightStyle
	 */
	public static void voidRight_BorderRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * ---------------------------------------- RIGHT-BOTTOM BORDER
	 * -------------------------------------------
	 */

	/*
	 * ---------------------------------------- LEFT-RIGHT-TOP BORDER
	 * -------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left-Right-Top
	 * @return VOID
	 */
	public static void voidLeft_BorderLeftRightTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Right-Top
	 * @return VOID
	 */
	public static void voidCenter_BorderLeftRightTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left-Right-Top
	 * @return VOID
	 */
	public static void voidRight_BorderLeftRightTop(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
	}
	/*
	 * ---------------------------------------- LEFT-RIGHT-TOP BORDER
	 * -------------------------------------------
	 */

	/*
	 * ---------------------------------------- LEFT-RIGHT-BOTTOM BORDER
	 * -------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left-Right-Bottom
	 * @return VOID
	 * @refer firstRowCellStyleBoldLeftBorderTopLeft
	 */
	public static void voidLeft_BorderLeftRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidLeft_BorderLeftRightBottomLightGrayBG(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Left, Rowspan=2
	 * @border Left-Right-Bottom
	 * @return VOID
	 * @refer
	 */
	public static void voidLeft_BorderLeftRightBottom_Rowspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setRowspan(2);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Right-Bottom
	 * @return VOID
	 * @refer contentAddressTitleRowWithBorder
	 */
	public static void voidCenter_BorderLeftRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidCenterPINKBG_BorderLeftRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.PINK);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Right-Bottom
	 * @return VOID
	 * @refer contentAddressTitleRowWithBorder
	 */
	public static void voidCenter_BorderLeftRightBottom_Colspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setColspan(2);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left-Right-Bottom
	 * @return VOID
	 */
	public static void voidRight_BorderLeftRightBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right, Colspan=6
	 * @border Left-Right-Bottom
	 * @return VOID
	 */
	public static void voidRight_BorderLeftRightBottom_Colspan6(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setColspan(6);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right, Rowspan=2
	 * @border Left-Right-Bottom
	 * @return VOID
	 * @refer
	 */
	public static void voidRight_BorderLeftRightBottom_Rowspan2(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setRowspan(2);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorder(0);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * ----------------------------------------- LEFT-RIGHT-BOTTOM BORDER
	 * ------------------------------------------
	 */

	/*
	 * ----------------------------------------- LEFT-TOP-BOTTOM BORDER
	 * ------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Left-Top-Bottom
	 * @return VOID
	 * @refer cellLabelLeftAlignTopBottomLeftBorderStyle
	 */
	public static void voidLeft_BorderLeftTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Left-Top-Bottom
	 * @return VOID
	 */
	public static void voidCenter_BorderLeftTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	public static void voidCenterPINKBG_BorderLeftTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.PINK);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Left-Top-Bottom
	 * @return VOID
	 * @refer cellLabelRightAlignTopBottomLeftBorderStyle
	 */
	public static void voidRight_BorderLeftTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthLeft(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * ---------------------------------------- LEFT-TOP-BOTTOM BORDER
	 * -------------------------------------------
	 */

	/*
	 * ---------------------------------------- RIGHT-TOP-BOTTOM BORDER
	 * -------------------------------------------
	 */
	/**
	 * @since 01-01-2018
	 * @align Left
	 * @border Right-Top-Bottom
	 * @return VOID
	 */
	public static void voidLeft_BorderRightTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pCell.setBorder(0);
		pCell.setPaddingLeft(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Center
	 * @border Right-Top-Bottom
	 * @return VOID
	 */
	public static void voidCenter_BorderRightTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pCell.setBorder(0);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}

	/**
	 * @since 01-01-2018
	 * @align Right
	 * @border Right-Top-Bottom
	 * @return VOID
	 * @refer cellLabelRightAlignTopBottomRightBorderStyle
	 */
	public static void voidRight_BorderRightTopBottom(PdfPCell pCell) {
		pCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pCell.setBorder(0);
		pCell.setPaddingRight(2f);
		pCell.setPaddingTop(1f);
		pCell.setPaddingBottom(4f);
		pCell.setBackgroundColor(BaseColor.WHITE);
		pCell.setBorderWidthRight(0.5f);
		pCell.setBorderWidthTop(0.5f);
		pCell.setBorderWidthBottom(0.5f);
	}
	/*
	 * ------------------------------------------- RIGHT-TOP-BOTTOM BORDER
	 * ----------------------------------------
	 */
}
