package com.catrionbe.api.controller;

 
import java.awt.image.BufferedImage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CourseUpdateRequest;
import com.catrionbe.api.model.QRCodeRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
@RequestMapping("/qr")
public class CCPQrCodeController {

	 
	@RequestMapping (value = "/GenerateQRCode",method = RequestMethod.POST, produces = MediaType.IMAGE_PNG_VALUE)
 //public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
 public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@RequestBody QRCodeRequest  qrcodeReq)

 
 
   throws Exception {

  QRCodeWriter barcodeWriter = new QRCodeWriter();
     BitMatrix bitMatrix = 
       barcodeWriter.encode(qrcodeReq.getQrCodeUrl(), BarcodeFormat.QR_CODE, 200, 200);

  return new ResponseEntity<>(MatrixToImageWriter.toBufferedImage(bitMatrix),HttpStatus.OK);
 }

}