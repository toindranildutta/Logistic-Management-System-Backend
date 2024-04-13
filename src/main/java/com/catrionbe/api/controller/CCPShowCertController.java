package com.catrionbe.api.controller;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.catrionbe.api.service.CCPSigningService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
@RequestMapping("/showqr")
public class CCPShowCertController {

	@Autowired
	private CCPSigningService objCCPSigningService;

	@GetMapping(value = "/{prNumber}")
	public ResponseEntity<?> fetchCertificate(@PathVariable("prNumber") String prNumber) throws Exception {
		System.out.println(" - - - - - -     ");
		objCCPSigningService.displaycertificate(prNumber);
		return ResponseEntity.ok(objCCPSigningService.displaycertificate(prNumber));

	}
}