package com.proyecto.ecommerce.backend.infraestructure.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.proyecto.ecommerce.backend.domain.model.DataPayment;
import com.proyecto.ecommerce.backend.domain.model.URLPaypalResponse;
import com.proyecto.ecommerce.backend.infraestructure.service.PaypalService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
public class PaypalController {
	private final PaypalService paypalService;
	private final String SUCCESS_URL = "https://proyecto-final-ecommerce-production.up.railway.app/payment/success";
	private final String CANCEL_URL = "https://proyecto-final-ecommerce-production.up.railway.app/payment/cancel";
	
	 @PostMapping
	    public URLPaypalResponse createPayment(@RequestBody DataPayment dataPayment){
	        try {
	            Payment payment = paypalService.createPayment(
	                    Double.valueOf(dataPayment.getAmount()),
	                    dataPayment.getCurrency(),
	                    dataPayment.getMethod(),
	                    "sale",
	                    dataPayment.getDescription(),
	                    CANCEL_URL,
	                    SUCCESS_URL
	            );
	            for (Links links : payment.getLinks()){
	                if(links.getRel().equals("approval_url")){
	                    return new URLPaypalResponse( links.getHref());
	                }
	            }
	        } catch (PayPalRESTException e) {
	            e.printStackTrace();
	        }

	        return new URLPaypalResponse("https://proyecto-final-ecommerce-production.up.railway.app") ;

	    }
	    @GetMapping("/success")
	    public RedirectView paymentSuccess(
	            @RequestParam("paymentId") String paymentId,
	            @RequestParam("PayerID") String payerId
	    ){
	        try {
	            Payment payment = paypalService.executePayment(paymentId, payerId);
	            if (payment.getState().equals("approved")){
	                return new RedirectView("https://proyecto-final-ecommerce-production.up.railway.app/payment/success");
	                //return new RedirectView("http://localhost:4200");
	            }
	        } catch (PayPalRESTException e) {
	            e.printStackTrace();
	        }
	        return new RedirectView("https://proyecto-final-ecommerce-production.up.railway.app");
	    }

	    @GetMapping("/cancel")
	    public  RedirectView patmentCancel(){
	        return new RedirectView("https://proyecto-final-ecommerce-production.up.railway.app");
	    }

	}
