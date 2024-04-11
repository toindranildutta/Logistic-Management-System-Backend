package com.catrionbe.api.model;

import java.io.Serializable;

public class QRCodeRequest  implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    
     private String qrCodeUrl;


	public String getQrCodeUrl() {
		return qrCodeUrl;
	}


	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

}
