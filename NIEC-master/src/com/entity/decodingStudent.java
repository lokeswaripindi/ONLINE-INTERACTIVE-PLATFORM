package com.entity;
import com.database.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

public class decodingStudent {
	
	
	public String rollToHex(String roll) throws Exception{   //01215602713 --> 0C10


		String branch=roll.substring(3,6),sno=roll.substring(0,3),
				year=roll.substring(9,11),collegeCode=roll.substring(6,9);
		
		if(!collegeCode.equals("027")){
			System.out.println("Invalid College code in Roll no");
		}
		String hex=Integer.toHexString(Integer.parseInt(sno)).toUpperCase();
		hex=hex+branchCode(Integer.parseInt(branch));
		hex=hex+findYearCode(Integer.parseInt(year));
		if(hex.length()==3){
			hex="0"+hex;    //TO MAKE 3 DIGIT CODE TO 4 DIGIT CODE--> FOR DELEMITER PUROPSE
		}
		
		return hex;
	}
	
	public 	String hexToRoll(String hex) throws Exception{  //  0C11-->01215602713


		String hex_sno=hex.substring(0,2),hex_branch=hex.substring(2, 3),hex_year=hex.substring(3, 4);String collegeCode="027";
		int sno=Integer.valueOf(hex_sno,16);//hex to decimal
		int branchNumber=branchCodeToNumber(Integer.parseInt(hex_branch));
		int year=findYearFromCode(Integer.parseInt(hex_year));
		String sn=sno+"";
		if(sn.length()==1){
			sn="00"+sno;
		}
		else if(sn.length()==2){
			sn="0"+sno;
		}
		String temp=""+sn+branchNumber+collegeCode+year+"";
		
		return temp;
	}
	
	public void decodeStudentList(String list,Boolean friends,student s,HashMap<String,String> hm) throws Exception,ClassNotFoundException, SQLException{  
		//true--> class friends false--> other friends
		String hexcode;
		ResultSet rs;
		studentDatabase sd=new studentDatabase();;
		//HashMap<String,String> hm =new HashMap<String,String>();
		if(friends){
			for(int i=0;i<list.length();i=i+4){
					hexcode=list.substring(i,i+4);
					rs=sd.fetchNameRoll(hexcode,s.s_branch);
					hm.put(rs.getString("S_ROLL"),rs.getString("S_NAME"));
			}
		}
		
		else{
			for(int i=0;i<list.length();i=i+4){
				hexcode=list.substring(i,i+4);
				rs=sd.fetchNameRoll(hexcode,branchName(findBranchFromRoll(hexToRoll(hexcode))));
				hm.put(rs.getString("S_ROLL"),rs.getString("S_NAME"));
			}
			
		}
	
		
	}
	
	
//***************************SUPPORTING FUNCITONS*******************	
	
	
//*******************EXTRACT VALUES FROM ROLL NO
	
	public int findSnoFromRoll(String roll) throws Exception{   //01215602713 --> 012
		return Integer.parseInt(roll.substring(0,3));
	}
	

	public int findBranchFromRoll(String roll)throws Exception{ //01215602713 --> 156
	
		return Integer.parseInt(roll.substring(3,6));
		
	}
	

	public int findYearFromRoll(String roll) throws Exception{    //01215602713 --> 13

		return Integer.parseInt(roll.substring(9,11));
	}
	

	public int findYearCode(int year) throws Exception{ // 13 --> 3+1 --> 4--> 4%4 --> 0 

		/*Calendar cal=Calendar.getInstance();  //DECODING
		int currentYear=cal.get(Calendar.YEAR);*/
		int temp=year%10;
		year=year/10;
		return (year+temp)%4;
	
	}
	
	public int findYearFromCode(int code) throws Exception{  // 0 --> 13  


		Calendar cal=Calendar.getInstance();  
		int currentYear=cal.get(Calendar.YEAR);//2016
		
		int temp=(currentYear%100)%10;  //6
		int temp1=(currentYear%100)/10;//1
		
		int temp3=(temp+temp1)%4;//3
		int temp5=temp3-code;
		//int temp4=(currentYear%1000)-temp3;//13
		int temp4=(currentYear-temp5);//2013
		return (temp4%100);  //13
		
	}
	
//******************BRANCH*****************
	
	
	
	
	public int branchCode(int branch) throws Exception{  //156 -->1
		int code=0;
		switch(branch){    //MAPS BRANCH CODES TO SINGLE DIGIT INTEGER VALUES
		case 156:{
			code=1;  //CSE
			break;
		}
		case 157:{
			code=2;  //ECE
			break;
		}
		case 158:{
			code=3; //EEE
			break;
		}
		case 159:{
			code=4;  //MECH
			break;
		}
		case 160:{
			code=5;  //IT
			break;
		}
		case 161:{
			code=6;  //CIVIL
			break;
		}
		default:{
			System.out.println("DEFAULT CASE OF branchCode() in decodingStudent class--> INVALID ROLL NO");
		}
			
		}
		return code;
		
	}
	
	
	public String branchName(int branch) throws Exception{  //156-->CSE

		String branchVal=null;
		switch(branch){    
		case 156:
		case 1:{
			 branchVal="CSE";
			break;
		}
		case 157:
		case 2:{
			branchVal="ECE";
			break;
		}
		case 158:
		case 3:{
			branchVal="EEE";
			break;
		}
		case 159:
		case 4:{
			branchVal="MECH";
			break;
		}
		case 160:
		case 5:{
			branchVal="IT";
			break;
		}
		case 161:
		case 6:{
			branchVal="CIVIL";
			break;
		}
		default:{
			System.out.println("DEFAULT CASE OF branchName() in decodingStudent class--> INVALID ROLL NO");
		}
			
		}
		return branchVal;
		
	}
	
	public int branchCodeToNumber(int code) throws Exception{  //1 --> 156

		int branch=0;
		switch(code){    
		case 1:{
			branch=156;  //CSE
			break;
		}
		case 2:{
			branch=157;  //ECE
			break;
		}
		case 3:{
			branch=158; //EEE
			break;
		}
		case 4:{
			branch=159;  //MECH
			break;
		}
		case 5:{
			branch=160;  //IT
			break;
		}
		case 6:{
			branch=161;  //CIVIL
			break;
		}
		default:{
			System.out.println("DEFAULT CASE OF branchCodetoNumber() in decodingStudent class--> INVALID ROLL NO");
		}
			
		}
		return branch;
		
	}
	
	
	
	
	
}
