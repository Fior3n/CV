package application;

import java.math.BigInteger;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {
	private BigInteger e;
	private BigInteger d;
	private BigInteger n;
	private BigInteger c;
	
	@FXML
	private GridPane gp;
	@FXML
	private Button shifr;
	@FXML
	private Button rozshifr;
	@FXML
	private Button random;
	
	@FXML
	private TextArea tatext;
	@FXML
	private TextArea tahash;
	@FXML
	private TextArea secret;
	@FXML
	private TextArea taget;
	@FXML
	private TextArea tafinal;
	
	@FXML
	private TextField tfkey;
	@FXML
	private TextField tfn;
	
	
	public void Shifr(ActionEvent actionEvent){
		BigInteger m = new BigInteger(hash(tatext.getText().toString()));
		BigInteger c = m.modPow(this.e, this.n);
		this.c=c;
		tahash.setText(hash(tatext.getText()));
		taget.setText(c.toString());
	}
	
	public void Rozshif(ActionEvent actionEvent){
		BigInteger r = this.c.modPow(this.d, this.n);
		tafinal.setText(unhash(r.toString()));
	}
	
	public void Random(ActionEvent actionEvent){
		BigInteger q=random(512), p=random(512);
		BigInteger n=q.multiply(p);
		BigInteger fn=q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));
		q=null; p=null;
		BigInteger e = new BigInteger("3");
		BigInteger d = fn.multiply(new BigInteger("2")).add(BigInteger.ONE).divide(e);
		int flag=0;
		int bit = 4;
		while(d.multiply(e).mod(fn).equals(BigInteger.ONE)==false){
			if(flag==1000){
				flag=0;
				bit++;
			}
			e=random(bit);
			if(e.equals(BigInteger.TEN))
				break;
			d = fn.multiply(new BigInteger("2")).add(BigInteger.ONE).divide(e);
			flag++;
			if(bit>64)
				break;
		}
		this.n=n;
		this.e=e;
		this.d=d;
		random.setVisible(false);
		gp.setVisible(true);
		tfkey.setText(e.toString());
		tfn.setText(n.toString());
		secret.setText(d.toString());
		
	}
	
	public static BigInteger random(int b){
		Random rand = new Random();
		BigInteger result = BigInteger.ONE;
		result=BigInteger.probablePrime(b, rand);
		return result;
	}
	
	public static String hash(String line){
		char[] mass= new char[line.length()];
		char[] res = new char[line.length()*3];
		mass=line.toCharArray();
		String result="";
		int k=0;
		for(int i=0; i<mass.length; i++){
			int temp = mass[i];
			if(temp<100){
				if(temp<10){
					res[k]=(char) 48;;
					res[k+1]=(char) 48;;
					res[k+2]=(char) (temp+48);
					k+=3;
				}
				else{
					res[k]=(char) 48;
					res[k+1]=(char) (temp/10+48);
					res[k+2]=(char) (temp%10+48);
					k+=3;
					
				}
			}
			else{
				res[k]=(char) (temp/100+48);
				res[k+1]=(char) ((temp/10)%10+48);
				res[k+2]=(char) (temp%10+48);
				k+=3;
			}
		}
		
		result=String.valueOf(res);	
		
		return result;
	}
	
	public static String unhash(String line){
		if(line.length()%3!=0){
			String main ="0";
			line=main+line;
		}
		char[] mass = new char[line.length()];
		mass=line.toCharArray();
		char[] res = new char[line.length()/3];
		int k=0;
		for(int i =0; i<mass.length; i+=3){
			res[k]=(char) (((int) mass[i]-48)*100+((int) mass[i+1]-48)*10+(int)mass[i+2]-48);
			k++;
		}
		String result=String.copyValueOf(res);
		
		return result;
	}
}
