/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.utils
 * File name: ExportInvoicePdf.java
 * Author: Sanero.
 * Created date: May 12, 2019
 * Created time: 10:31:26 AM
 */

package dev.sanero.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import dev.sanero.entity.HouseHold;
import dev.sanero.entity.Room;
import dev.sanero.entity.Service;
import dev.sanero.entity.ServiceType;
import dev.sanero.entity.User;

public class ExportInvoicePdf extends AbstractPdfView{
  Font fontItalic = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);
  Font fontBold = new Font(Font.HELVETICA, 12, Font.BOLD);
  Font fontNormal = new Font(Font.HELVETICA, 12, Font.NORMAL);
  
  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document,
      PdfWriter writer, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    Service s = (Service) model.get("invoice");
    Date date = new Date();
    response.setHeader("Content-Disposition", "attachment; filename=\"" + s.getRoom().getName() 
        + "_" + s.getCollectMonth()
        + "_export_" + date.getTime() + ".pdf\"");

    String pageTitle = "HOÁ ĐƠN " + s.getServiceType().getName().toUpperCase();
    document.setPageSize(new Rectangle(100, 100, 100, 100));
//    document.setPageSize(PageSize.A5.rotate());
    BaseFont bf = BaseFont.createFont("fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//    Font fontBold = new Font(bf, 12, Font.BOLD);
    Font fontNormal = new Font(bf, 12, Font.NORMAL);
    Font f = new Font(bf, 16, Font.BOLD);

    Paragraph phN = new Paragraph(pageTitle, f);
    phN.setAlignment(Rectangle.ALIGN_CENTER);
    document.add(phN);
   
    PdfPTable table = new PdfPTable(2);
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_JUSTIFIED);
    float[] colWidths1 = { 75, 100 };
    table.setWidths(colWidths1);
    
    table.addCell(getLeft(fontNormal));
    table.addCell(getRight(fontNormal, s));
    
    PdfPTable dataTable = new PdfPTable(5);
    dataTable.setSpacingBefore(40);
    dataTable.getDefaultCell().setBorder(Rectangle.BOX);
    dataTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    dataTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_JUSTIFIED);
    dataTable.addCell(createCell(fontNormal, "Chỉ số cũ",  1, true));
    dataTable.addCell(createCell(fontNormal, "Chỉ số mới",  1, true));
    dataTable.addCell(createCell(fontNormal, "Chỉ số tiêu thụ",  1, true));
    dataTable.addCell(createCell(fontNormal, "Đơn giá",  1, true));
    dataTable.addCell(createCell(fontNormal, "Thành tiền",  1, true));
    
    if (s.getDetail() != null && !"".equals(s.getDetail())) {
      String index[] = s.getDetail().split("-");
      
      double use = Double.parseDouble(index[1]) - Double.parseDouble(index[0]);
      
      List<Double> m = new ArrayList<>();
      List<Double> p = new ArrayList<>();
      List<Double> arr = new ArrayList<>();
      List<Double> arr1 = new ArrayList<>();
      String increase = s.getIncrease();
      double price = 0;
      
      ServiceType type = s.getServiceType();
      if (!"".equals(increase)) {
        for (String str : increase.split(";")) {
           String temp[] = str.split(" - ");
           m.add(Double.parseDouble(temp[0]));
           p.add(Double.parseDouble(temp[1]));
        }
        
        double tempo = use;
        int size = m.size();
        for (int i = 0; i < size; i++) {
          if (use > m.get(i)) {
            double t = m.get(i);
            if (i > 0) {
              t -= m.get(i-1);
            }
            double pp = 0;
            if (i < size - 1) {
              tempo -= t;
              pp += t * p.get(i);
              arr.add(t);
            } else {
              pp += tempo * p.get(i);
              arr.add(tempo);
            }
            price += pp;
            arr1.add(pp);
          } else {
            double pp = 0;
            pp += tempo * p.get(i);
            price += pp;
            arr.add(tempo);
            arr1.add(pp);
            break;
          }
        }
      } else {
        price = use * type.getPrice();
      }
      

      dataTable.addCell(createCell(fontNormal, index[0],  1, true));
      dataTable.addCell(createCell(fontNormal, index[1],  1, true));
      dataTable.addCell(createCell(fontNormal, use + "",  1, true));
      if(arr.size() == 0) {
        dataTable.addCell(createCell(fontNormal, type.getPrice() + "",  1, true));
        dataTable.addCell(createCell(fontNormal, price + "",  1, true));
      } else {
        dataTable.addCell(createCell(fontNormal, "",  1, true));
        dataTable.addCell(createCell(fontNormal, "",  1, true));
      }
      
      if (arr.size() > 0) {
        for (int i = 0; i < arr.size(); i++) {
          dataTable.addCell(createCell(fontNormal, "",  1, true));
          dataTable.addCell(createCell(fontNormal, "",  1, true));
          
          dataTable.addCell(createCell(fontNormal, arr.get(i) + "",  1, true));
          dataTable.addCell(createCell(fontNormal, p.get(i).longValue() + "",  1, true));
          dataTable.addCell(createCell(fontNormal, (arr1.get(i).longValue()) + "",  1, true));
        }
      }
      
      
      dataTable.addCell(createCell(fontNormal, "Thuế GTGT 10% ",  4, true));
      dataTable.addCell(createCell(fontNormal,  (long) (price * 0.1) + "",  1, true));
      
      dataTable.addCell(createCell(fontNormal, "Tổng cộng tiền thanh toán ",  4, true));
      dataTable.addCell(createCell(fontNormal, (long) (price * 1.1) + "",  1, true));
      dataTable.addCell(createCell(fontNormal, "Số tiền bằng chữ: " + NumberToWord.convert((long) (price * 1.1)) + " đồng",  5, true));
    }
    else {
      table.addCell(createCellLeft(fontNormal, "Tổng tiền thanh toán:"));
      table.addCell(createCellRight(fontNormal,(long) s.getPrice() + ""));
      table.addCell(createCell(fontNormal, "Số tiền bằng chữ: " + NumberToWord.convert((long) s.getPrice()) + " đồng",  5, false));
    }
    Paragraph paragraph = new Paragraph();
    paragraph.add(new Chunk(""));

    
    PdfPTable tabletmp = new PdfPTable(1);
    tabletmp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    tabletmp.setWidthPercentage(100);
    tabletmp.addCell(table);
    tabletmp.setSpacingAfter(10);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    
    if (s.getDetail() != null && !"".equals(s.getDetail())) {
      tabletmp.addCell(dataTable);
    }
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    tabletmp.addCell(paragraph);
    
    PdfPTable signTable = new PdfPTable(6);
    signTable.setSpacingBefore(40);
    signTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    signTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    signTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
    signTable.addCell(createCell(fontNormal, "",  4, false));
    signTable.addCell(createCell(fontNormal, "Người lập hoá đơn",  2, false));
    signTable.addCell(createCell(fontNormal, "",  4, false));
    signTable.addCell(createCell(fontNormal, s.getEmployee().getName(),  2, false));
    tabletmp.addCell(signTable);
    
    document.add(tabletmp);
  }

  private PdfPCell getLeft(Font fontNormal) {
    Chunk chunkDate = new Chunk("\nKể từ ngày: ", fontNormal);
    Phrase phDate = new Phrase(chunkDate);
    Chunk chunkSupplier = new Chunk("\nNhà cung cấp: ", fontNormal);
    Phrase phSupplier = new Phrase(chunkSupplier);
    Chunk chunkUserFullname = new Chunk("\nKhách hàng: ", fontNormal);
    Phrase phUserFullName = new Phrase(chunkUserFullname);
    Chunk chunkAddress = new Chunk("\nCăn hộ:", fontNormal);
    Phrase phAddress = new Phrase(chunkAddress);
    
    Paragraph phN = new Paragraph();
    phN.add(phDate);
    phN.add(phSupplier);
    phN.add(phUserFullName);
    phN.add(phAddress);
    phN.setIndentationLeft(20);
    PdfPCell cellLeft = new PdfPCell();
    cellLeft.setBorder(Rectangle.NO_BORDER);
    cellLeft.addElement(phN);
    cellLeft.setPadding(5);
    return cellLeft;
  }
  
  private PdfPCell getRight(Font fontNormal, Service s) {
    String date[] = s.getCollectMonth().split("-");
    String dateStr = "20-" + (Integer.parseInt(date[0]) - 1) + "-" + date[1] + " đến 20-" + s.getCollectMonth();
    Chunk chunkDate = new Chunk("\n" + dateStr, fontNormal);
    Phrase phDate = new Phrase(chunkDate);
    Chunk chunkSupplier = new Chunk("\n" + s.getServiceType().getSupplier(), fontNormal);
    Phrase phSupplier = new Phrase(chunkSupplier);
    Room r = s.getRoom();
    int hh = r.getHousehold();
    String fullName = "";
    for(HouseHold h: r.getHouseholds()) {
      if (h.getId() == hh) {
          int user = h.getUserId();
          for(User u: h.getUsers()) {
            if (user == u.getId()) {
              fullName = u.getName();
              break;
            }
          }
          break;
      }
    }
    Chunk chunkUserFullname = new Chunk("\n" + fullName, fontNormal);
    Phrase phUserFullName = new Phrase(chunkUserFullname);
    String add = r.getName() + " - " + r.getFloor().getName() + " - " + r.getBuilding().getName();
    Chunk chunkAddress = new Chunk("\n" + add, fontNormal);
    Phrase phAddress = new Phrase(chunkAddress);

    Paragraph phN = new Paragraph();
    phN.add(phDate);
    phN.add(phSupplier);
    phN.add(phUserFullName);
    phN.add(phAddress);
    phN.setIndentationLeft(30);
    phN.setIndentationRight(20);
    PdfPCell cellLeft = new PdfPCell();
    cellLeft.setBorder(Rectangle.NO_BORDER);
    cellLeft.addElement(phN);
    cellLeft.setPadding(5);
    return cellLeft;
  }
  
  private PdfPCell createCell(Font fontNormal, String text, int colSpan,boolean border) {
    Chunk chunk = new Chunk(text, fontNormal);
    Phrase phrase = new Phrase(chunk);
    Paragraph phN = new Paragraph();
    phN.add(phrase);
    phN.setIndentationLeft(20);
    PdfPCell cell = new PdfPCell();
    if (border)
      cell.setBorder(Rectangle.BOX);
    else 
      cell.setBorder(Rectangle.NO_BORDER);
    cell.addElement(phN);
    cell.setPadding(3);
    cell.setColspan(colSpan);
    return cell;
  }
  
  private PdfPCell createCellLeft(Font fontNormal, String text) {
    Chunk chunk = new Chunk(text, fontNormal);
    Phrase phrase = new Phrase(chunk);
    Paragraph phN = new Paragraph();
    phN.add(phrase);
    phN.setIndentationLeft(20);
    PdfPCell cellLeft = new PdfPCell();
    cellLeft.setBorder(Rectangle.NO_BORDER);
    cellLeft.addElement(phN);
    cellLeft.setPadding(5);
    return cellLeft;
  }
  
  private PdfPCell createCellRight(Font fontNormal, String text) {
    Chunk chunk = new Chunk(text, fontNormal);
    Phrase phrase = new Phrase(chunk);
    Paragraph phN = new Paragraph();
    phN.add(phrase);
    phN.setIndentationLeft(30);
    phN.setIndentationRight(20);
    PdfPCell cell = new PdfPCell();
    cell.setBorder(Rectangle.NO_BORDER);
    cell.addElement(phN);
    cell.setPadding(5);
    return cell;
  }
}
