package employee;

import java.util.Scanner; 

class Main {
	 public static void main(String[] args) {
	        try
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded Successfully");
			}
			catch(ClassNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
	        System.out.println("Enter 1 for user, 2 for Manager, 3 for clerk: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch(choice) {
            case 1:
            	Customer us = new Customer();
            	us.applyLoan();
              break;
            case 2:
            	System.out.println("Enter 1 to show the status, 2 to update the customer loan status");
                Scanner type = new Scanner(System.in);
                int x=type.nextInt();
                Manager mg=new Manager();
                if(x==1) {
                	mg.show();
                }else if(x==2) {
                	mg.setStatus();
                }else {
                	System.out.println("Enter valid choice");
                }
            	break;
            case 3:
            	Clerk cl=new Clerk();
            	System.out.println("Enter 1 to add user, 2 to show the status,3  to uplaod image documents");
                Scanner typec = new Scanner(System.in);
                int xc=typec.nextInt();
                if(xc==1) {
                	cl.addUser();
                }else if(xc==2) {
                	cl.show();
                }else if(xc==3){
                	cl.addImg();
                }else {
                	System.out.println("Enter valid choice");
                }
               break;
            default:
              System.out.println("Enter valid user");
          }
	        
	    }
}

