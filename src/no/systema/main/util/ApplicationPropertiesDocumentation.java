/**
 * 
 */
package no.systema.main.util;

/**
 * This class is the documentation of all customers setup.
 * Since the file will be stored in binary format it has been moved from the previously commented application.properties (plain-text)
 * 
 * application.properties contains now one-and-only-one host string. Depending on the customer's installation parameter.
 * This file should be regarded as the master information before any deployment to a new customer
 * 
 * 
 * @author oscardelatorre
 * @date Mar 25, 2015 
 * 
 */
public class ApplicationPropertiesDocumentation {
		/*
		#myProperty=CLINTAN
		#Remote Desktop: 10.13.1.221 - oscar/stromstad
		#Systema mail: http://gw.systema.no:440/mail/odelator.nsf -> oscar de la torre/stromstad


		#VERSION
		version.spring=3.1
		version.espedsg=15.25.mrs


		# SYSTEMA
		#CGI services - Host name for all CGI back-end calls
		#http.as400.root.cgi=http://gw.systema.no
		#Java services
		#http.syjservices.root.servlet=http://gw.systema.no:8080


		#=======================================================================
		#KINGSROD (OK) - Tomcat Mgr http://10.20.4.2:8181 (Kings NO & Kings DK)
		#VPN is needed:
		#Host 64.28.16.138:4433, 
		(NO)
		#User=systema Pass=fodd
		(DK) 
		#User=bog Pass=after8 
		#Domain=kingsrod
		#=======================================================================
		#http.as400.root.cgi=http://10.20.4.2

		#===============================================================================
		#POSTNORD (OK) - Tomcat Mgr http://192.108.100.247:8080 (AS400--> rem postnord)
		#===============================================================================
		#TEST (utv-env) -->espedsg: NIAM/test
		#http.as400.root.cgi=http://192.108.100.247

		#PROD -->espedsg: NIAM/test
		#http.as400.root.cgi=http://10.47.1.11

		#=================================================
		#NORTRAIL - Tomcat Mgr http://193.69.249.50:8080 
		#=================================================
		#PROD och TEST (utv-env med VPN) --> KADMONSG/MON
		#http.as400.root.cgi=http://193.69.249.50:54321

		#===================================================================
		#DSV - Tomcat Mgr http://10.47.16.52:8080 (AS400 = SYSTEMA/SY072010)
		#Teamviewer: helpdesk@systema.no, pass:straffe12
		#Windows session: administrator/barkrakk
		#===================================================================
		#PROD -->espedsg: TSTINT/INT
		#http.as400.root.cgi=http://10.47.16.52

		#=======================================================================================
		#DHL - Tomcat Mgr http://165.72.19.220:8181 (AS400 = SYSTEMA/JUNI2011)
		#Trix från Roger/Oscar när vi bytte till port 8443: http://198.141.241.224:8443/espedsg
		#Detailed chain-of-events in AS400 
		#--START--
		# (1) START- Documents - DHL...ws
		# (2) then: SYSTEMA/juni2011
		# (3) Larvik: ert follow by return
		
		#--END---
		#PROD -->espedsg: OLROS/After8 and TDS: <Sign:AMRYE, pass:after8>, alt 2:<Sign:OLROS, pass:after8>
		#PROD -->espedsg (alternative with SYSTEMA) as user: SYSTEMA/After8 and TDS: <Sign: SYS, pass:After8>
		#========================================================================================
		#http.as400.root.cgi=http://165.72.19.220

		#=======================================================================================
		#TOTEN TRANSPORT - Tomcat Mgr http://192.168.1.250 (SPØRR.OPP. och PRISKALK) vs http://82.196.200.72 (OBSOLETE)
		#User/pwd: MSKJOLD/MS  OBS:-->TOMCAT: http://cust.toten.as:8080/espedsg
		#========================================================================================
		#http.as400.root.cgi=http://192.168.1.250 
		
		#===========================================================================================
		#ASP-KUNDER - Tomcat Mgr http://10.13.1.22:8181 = http://gw.systema.no:8181 
		#NOTE: We use the ip in order to make ASP-kunder a bit more explicit for us developers.
		#User/pwd:(t.ex. Dachser Norway...check AS/400)   OBS:-->TOMCAT: http://10.13.1.22:8181
		#Service-pgm går fysiskt till: 10.13.1.22:65432 (vi skulle kunna ange DNS=aspweb.systema.no)
		#===========================================================================================
		#A24 - Dachser
		#http.as400.root.cgi=http://10.13.1.22:65432
		#Java services
		#http.syjservices.root.servlet=http://a24.systema.no:8181
		#A38 - WML
		#http.as400.root.cgi=http://10.13.1.22:65453
		#Java services
		#http.syjservices.root.servlet=http://a24.systema.no:8282
		
		
		#=========================================================================
		#Bring Norge - Tomcat Mgr http://139.114.151.52:8080 
		#Service pgm skall gå mot: syarc.bring.com (espedsg: User JOVO, pass=test), 
		#real user espedsg: tommyb/tb
		#=========================================================================
		#http.as400.root.cgi=http://syarc.bring.com
		
		*/
}
