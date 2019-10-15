//105403517
//���V�a
//���3A
import java.lang.reflect.MalformedParametersException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	public static void main(String []args) {
		//���
		Invoice[] invoices = {
				 new Invoice(83, "Electric sander", 7, 57.98),
				 new Invoice(24, "Power saw", 18, 99.99),
				 new Invoice(7, "Sledge hammer", 11, 21.50),
				 new Invoice(77, "Hammer", 76, 11.99),
				 new Invoice(39, "Lawn mower", 3, 79.50),
				 new Invoice(68, "Screwdriver", 106, 6.99),
				 new Invoice(56, "Jig saw", 21, 11.00),
				 new Invoice(3, "Wrench", 34, 7.50),
				 new Invoice(45, "Wrench", 13, 7.50),
				 new Invoice(22, "Hammer", 47, 11.99)
		 	 	 };
		//�N����ରlist
		List<Invoice> list = Arrays.asList(invoices);
		Scanner input = new Scanner(System.in);
		String choice="-1";
		
		//����j�骽���J-1
		do {
		
		System.out.println("Welcome to invoices management system.");
		System.out.println("Functions: Report/Select");
		System.out.printf("%s","Choices (-1 to exit): ");
		choice = input.nextLine();
		
		
		
		switch(choice.toLowerCase()) {
		case "report":
			
			//Function<Invoice,Integer> quantity = Invoice::getQuantity;
			//Function<Invoice,Double> price = Invoice::getPrice;
			
			//Comparator<Invoice> sumofSingle = 
				//	Comparator.comparing();
			
			Map<String, Double> groupByDescription = 
					list.stream()
						.collect(Collectors.groupingBy(Invoice::getPartDescription,  //��@��Description���F���b�@�_
								//TreeMap::new,
								Collectors.summingDouble(x->x.getPrice()*x.getQuantity())));	//�N�C��key �����쪺value��  ���̼˪���O�ìۥ[
								//�o�ӮɭԪ�key�Odescription  value �O�@��description ����price*quantity�ìۥ[
			
			
			Map<String,Double> sortedGroup = 
			//List sortedGroup=
			groupByDescription.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())     //�Hvalue ���Ƨ�
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(k,v)->k,LinkedHashMap::new));
			//.collect(Collectors.toList());
			
			//sortedGroup.forEach(System.out::println);
			System.out.printf("%nInvoices groupby description:%n");
			sortedGroup
			.forEach((k,v)->System.out.printf("description: %-17sInvoice Amount:%16.2f\n",k,v));
			
			
			//.forEach((description,invoice)->
				//		System.out.printf("description: %s      %.2f\n",description,invoice)
						//Invoice.forEach(x->System.out.println(x.getPrice()*x.getQuantity()));
						
						
					//);
				break;
		case"select":
			System.out.printf("Input the range to show:(min,max): ");
			String minmaxStr = input.nextLine();    //�s����J���F��
			String minmax[] = minmaxStr.split(",");   //�H�Ů���}
			int minmaxInt[] = new int[2];      //�Ů���}���F���ഫ��int �}�C
			for(int i=0;i<2;i++) {
				minmaxInt[i] = Integer.parseInt(minmax[i]);
				//System.out.println(minmaxInt[i]);
			}//end for
			
			
		/*	Map<String, Double> groupByDescription2 = 
					list.stream()
						.collect(Collectors.groupingBy(Invoice::getPartDescription,
								//TreeMap::new,
								Collectors.summingDouble(x->x.getPrice()*x.getQuantity())));
			*/
			Map<Double,String> groupByDescription2 = 
					list.stream()                  //�ন�Hprice*quantity ��key description ��value
						.collect(Collectors.toMap(x->x.getPrice()*x.getQuantity(),Invoice::getPartDescription ));
					
		/*	
			Map<String,Double> sortedGroup2 = 
			//List sortedGroup=
			groupByDescription2.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.filter(k->k.getValue()>minmaxInt[0]&&k.getValue()<minmaxInt[1])
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(k,v)->k));
			//.collect(Collectors.toList());
			
			//sortedGroup.forEach(System.out::println);
			System.out.printf("%nInvoices groupby description:%n");
			sortedGroup2
			.forEach((k,v)->
			System.out.printf("description: %-17sInvoice Amount:%16.2f\n",k,v));
			*/
			
			Map<Double,String> sortedGroup2 = 
					//List sortedGroup=
					groupByDescription2.entrySet().stream()
					.sorted(Map.Entry.comparingByKey())     //�Hkey ���Ƨ�
					.filter(k->k.getKey()>minmaxInt[0]&&k.getKey()<minmaxInt[1])   
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(k,v)->k));
					//.collect(Collectors.toList());
					
					//sortedGroup.forEach(System.out::println);
					System.out.printf("%nInvoices map to description and invoice amount for invoices in the range %d-%d:%n",
							minmaxInt[0],minmaxInt[1]);
					sortedGroup2
					.forEach((k,v)->
					System.out.printf("description: %-17sInvoice Amount:%16.2f\n\n",v,k));
			
			break;
		case"-1":
			
			break;
		
		}//end switch
		
		
		}while(!choice.equals("-1"));
		
		
	}//end main
}//end class Main
