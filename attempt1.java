
import java.util.*;
public class attempt1
{
   public static void main(String[] args){
       Scanner in = new Scanner(System.in);
       
        FileIO reader = new FileIO();
        Scanner sc = new Scanner(System.in);
 
        String[] input= reader.load("1000airports.txt");
        //this table will now just separate the string into secions
        String[][] table1 = new String [(input.length)][4];
        
        for (int i = 0; i<input.length; i++)
        {
            String[] temp = input[i].split(",");
            table1[i][0]=temp[0];
            table1[i][1]=temp[1];
            table1[i][2]=temp[2];
            table1[i][3]=temp[3];
        }
        
        String[][] coordinates=new String [(input.length)][2];
         for (int i = 0; i<table1.length; i++)
        {
            coordinates[i][0]=table1[i][2];
            coordinates[i][1]=table1[i][3];
        }
            
        //double value = Double.parseDouble(text);
        double[][] newCoordinates= new double[coordinates.length][2];
        for(int i=0;i<newCoordinates.length;i++)
        {
           newCoordinates[i][0]=Double.parseDouble(coordinates[i][0]);
           newCoordinates[i][1]=Double.parseDouble(coordinates[i][1]);
            
        }
        
        //create a boolean to see if a town has been visited
        boolean[] isVisited=new boolean[1000];
        for(int i=0;i<input.length;i++)
        {
            isVisited[i]=false;
        }
        
        //start at town number 1 and get the next closest town and continue
        double Totaldistance=0; //keeps track of total distance covered
        isVisited[0]=true;
        String path=new String("1");
        double xValue=newCoordinates[0][0];
        double yValue=newCoordinates[0][1];
        int tracker=-10;//keep track of town number
        double[][] ar =new double[1000][3];
        ar[0][0]=1;
        ar[0][1]=xValue;
        ar[0][2]=yValue;
        int count=1;
        
       for(int j=0;j<input.length-1;j++)
       {
           double shortest=1000000000;
        for(int i=1;i<input.length;i++)
        {   
            double distance=dist(xValue,yValue,newCoordinates[i][0],newCoordinates[i][1]);
            if(distance<shortest && isVisited[i]==false)
            {
                shortest=distance;
                tracker=i+1;
            }
        }
        Totaldistance=Totaldistance+shortest;
        isVisited[tracker-1]=true;
        xValue=newCoordinates[tracker-1][0];
        yValue=newCoordinates[tracker-1][1];
        ar[count][0]=tracker;
        ar[count][1]=xValue;
        ar[count][2]=yValue;
        path=path+"." + tracker;
        count++;
      }
      System.out.println(path);
     // System.out.println(Totaldistance);
      
      
    }
    

   
   public static double total(double [][] array)
   {
       double sum=0;
       for(int i=0; i<999; i++)
       {
           sum = sum+(dist(array[i][1],array[i][2],array[i+1][1],array[i+1][2]));
       }
       return sum;
   }
   
    
    public static double dist(double x1, double y1, double x2, double y2)
    {
      
       
       final int R = 6371; // Radious of the earth
        double lat1 =x1;
        double lon1 =y1;
        double lat2 =x2;
        double lon2 =y2;
        double latDistance = toRad(lat2-lat1);
        double lonDistance = toRad(lon2-lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
       
    }
    
    
    private static Double toRad(Double num) {
        return num * Math.PI / 180;
    }
    

    
}