package com.pfe.websocket.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.pfe.Entity.Constraint_CO2;
import com.pfe.Entity.Device;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.Alert.AlertKey;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.History.HistKey;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Entity.RealTime.RTKey;
import com.pfe.Entity.RealTime.Rt_CO2;
import com.pfe.Repository.AlertRepository;
import com.pfe.Repository.ConstraintRepository;
import com.pfe.Repository.DeviceRepository;
import com.pfe.Repository.HistoryRepository;
import com.pfe.Repository.RtRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.exception.ResourceNotFoundException;
import com.pfe.websocket.models.Greeting;
import com.pfe.websocket.models.HelloMessage;


@RestController
public class GreetingController {

	
	@Autowired
	DeviceRepository dr;
	@Autowired
	SubUserRepository sur;
	@Autowired
	UserRepository ur;
	
	@Autowired
	ConstraintRepository cr;
	
	@Autowired
	AlertRepository ar;
	@Autowired
	RtRepository rtr;
	@Autowired
	HistoryRepository hr;
	
	
    @MessageMapping("/hello/{cinu}/{reference}")
    @SendTo("/topic/greetings")
    public Greeting greeting(@DestinationVariable Long cinu,@DestinationVariable String reference,HelloMessage message) throws Exception {
    	Thread t=new Thread();
    	t.start();
    do
    	{
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown user with cin: " + cinu));
		
		List <Device> lista = this.ur.listDevicesPerUser(cinu);
	
		Device d = dr.findByReference(reference) ;
		Rt_CO2 rt = new Rt_CO2();	
		RTKey rtk=new RTKey();
		rtk.setReference(d.getReference());
		rt.setRtk(rtk);
		Float v=(float)(Math.random() * 3.5*10+1/7);
		rt.setValue_co2(v);
		rt.setDate(new Date());
		HistKey hk=new HistKey(d.getReference(),rt.getDate());
		
		History_CO2 h = new History_CO2();
		h.setHk(hk);
		h.setValue(v);
		this.hr.save(h);
		
		Alert_C02 a =new Alert_C02();
		AlertKey ak=new AlertKey(d.getReference(),h.getHk().getDate());
		
		a.setAk(ak);
		Long c = d.getIdConstraint();
		Constraint_CO2 ct = this.cr.findByIdConstraint(c);
		
		if(ct.getMin_value()>v)
		{	a.setMessage(" co2 rate is very LOW!!");
		this.ar.save(a);}
		
		 if (ct.getMax_value()<v) {
				a.setMessage(" co2 rate is very HIGHT!!");
				this.ar.save(a);
		 }
		 this.rtr.save(rt);
		 Thread.sleep(8500);
		 rt.setValue_co2(v);
		 rt.setDate(new Date());

    	}
	while (!(t.isAlive()));
	//	if (lista.contains(d)==true) {throw new Exception("device is not in the list!");}
	/*	for(int i =0;i<lista.size();i++) {
			
	
			
	
			
	
			 
	
			
			for(int j=0;j<5;j++) {
				/* Thread.sleep(1000); 
				
				
				
	
				
			if(ct.getMin_value()>(float)(Math.random() * j*10+1/7))
			{			Rt_CO2 rt = new Rt_CO2();	
				rt.setValue_co2((float)(Math.random() * j*10+15/7));	
			Date da = new Date();
			rt.setDate(da);
			rt.setReference(reference);
			this.rtr.save(rt);
				Alert_C02 a = new Alert_C02();
				a.setMessage(" co2 rate is very LOW!!");
				this.ar.save(a);
				
				HistKey histkey = new HistKey();
				histkey.setIdAlert(a.getIdAlert());
				histkey.setIdRt(rt.getIdRt());
				
				History_CO2 h = new History_CO2();
				h.setHk(histkey);
				h.setDateHistory(da);
				this.hr.save(h);
			}
			else if (ct.getMax_value()<(float)(Math.random() * j*10+1500/7)) {
				{	Rt_CO2 rt = new Rt_CO2();	
				rt.setValue_co2((float)(Math.random() * j*10+1500/7));	
				Date da = new Date();
				rt.setDate(da);
				rt.setReference(reference);
				this.rtr.save(rt);
				Alert_C02 a = new Alert_C02();
				a.setMessage(" co2 rate is very HIGH !!");
				this.ar.save(a);
				
				HistKey histkey = new HistKey();
				histkey.setIdAlert(a.getIdAlert());
				histkey.setIdRt(rt.getIdRt());
				
				History_CO2 h = new History_CO2();
				h.setHk(histkey);
				h.setDateHistory(da);
				this.hr.save(h);
			}
			
			}
	    
    }}*/
			return new Greeting("Hello, " + cinu+HtmlUtils.htmlEscape(message.getName()) + "!");	
    }
    

}
  
    

