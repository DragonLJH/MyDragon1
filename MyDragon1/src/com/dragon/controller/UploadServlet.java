package com.dragon.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dragon.common.Management;
import com.dragon.common.StringConstant;
import com.dragon.service.UploadService;

/**
 * Servlet implementation class UploadServlet
 */
//上传文件最大为10m字节，若超界会在getPart或getParts方法调用时报FileSizeLimitExceededException
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/upload")
public final class UploadServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UploadServlet() {
    super();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("utf-8"); // 设置请求数据的解码字符集
    
    // 得到上传文件的存放路径
    ServletContext servletContext = this.getServletConfig()
        .getServletContext();
    String path = servletContext.getInitParameter("upload_dragon");

    // 若文件大小超界，将抛出IllegalStateException(根源是FileSizeLimitExceededException)
    Part filePart = request.getPart("images"); // 可支持上传一个文件
    // Collection fileParts = request.getParts(); // 可支持上传多个文件
    String fileName = filePart.getSubmittedFileName(); // 得到上传文件名
    fileName = generateUniqueFileName(fileName); // 生成唯一文件名

    Map resultMap = uploadFile(path, filePart, fileName);

    request.setAttribute(StringConstant.REQ_UPLOAD,
        resultMap.get("STATUS_MSG"));

    String statusCode = (String) resultMap.get("STATUS_CODE");
    if ("1".equals(statusCode)) {
      // 调用后台模块保存图片信息
      UploadService uploadService = UploadService.INSTANCE;
      Management management = new Management();
      management.setName(request.getParameter("name"));
      management.setAuthor(request.getParameter("author"));
      management.setSummary(request.getParameter("summary"));
      management.setQuantity(Integer.parseInt(request.getParameter("quantity")));
      management.setType(request.getParameter("type"));
      management.setImages(fileName); // 保存文件的相对路径（包括文件名）

      uploadService.createAll(management);
      
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/upload.jsp");
    dispatcher.forward(request, response);
  }

  /*
   * 上传文件并返回结果信息
   */
  private Map uploadFile(String path, Part filePart, String fileName)
      throws IOException {

    Map resultMap = new HashMap();

    String contentType = filePart.getContentType(); // 获取上传文件的内容类型

    // 若不是图片，则报错。若要支持其他格式，则需要更改此处
    if (contentType == null || !contentType.startsWith("image")) {
      String message = "仅支持图片上传，该文件内容类型不支持，contentType=" + contentType;
      resultMap.put("STATUS_MSG", message);
      resultMap.put("STATUS_CODE", "3");

      return resultMap;
    }

    OutputStream out = null;
    InputStream fileContent = null;

    try {
      String pathname = path + File.separator + fileName; // 得到上传文件路径+文件名
      out = new FileOutputStream(new File(pathname)); // 创建输出流
      fileContent = filePart.getInputStream(); // 得到输入流，即文件内容

      final byte[] bytes = new byte[1024]; // 创建数组，准备每次读1024字节，即1k

      // 为了提高输出效率，每次从文件内容中读1024字节，然后输出。也可用BufferedOutputStream。
      // 直至read返回-1，表示读完了。
      int read = fileContent.read(bytes); // 读到的字节数
      while (read != -1) {
        out.write(bytes, 0, read); // 写，即输出
        read = fileContent.read(bytes); // 接着读
      }

//        // 用BufferedOutputStream实现输出。
//          BufferedOutputStream bufOut = new BufferedOutputStream(out);
//          int read = fileContent.read();
//          while (read != -1) {
//              bufOut.write(read);
//              read = fileContent.read();
//          }

      String message = fileName + "图片创建成功，路径是：" + path;
      resultMap.put("STATUS_MSG", message);
      resultMap.put("STATUS_CODE", "1");

    } catch (FileNotFoundException e) {
      String message = fileName + "创建失败，路径是：" + path;
      resultMap.put("STATUS_MSG", message);
      resultMap.put("STATUS_CODE", "2");

      e.printStackTrace(); // 打印错误信息，此处最好用日志工具，比如log4j
    } finally {
      if (out != null) {
        out.close();
      }
      if (fileContent != null) {
        fileContent.close();
      }
    }
    return resultMap;
  }

  /*
   * 返回的新唯一文件名 = UUID + 原文件的后缀名。
   */
  private String generateUniqueFileName(String originalName) {
    if (originalName == null) {
      return null;
    }

    // 生成唯一的文件名如下，以“宋智孝.jpg”为例
    int suffixIndex = originalName.lastIndexOf("."); // 取得后缀的位置，如3
    String suffix = originalName.substring(suffixIndex); // 取得后缀，如.jpg

    String uuid = UUID.randomUUID().toString(); // 生成UUID，并转化为String
    uuid = uuid.replaceAll("-", ""); // 去掉UUID的-符号

    String uniqueName = uuid + suffix;

    return uniqueName;
  }

}
