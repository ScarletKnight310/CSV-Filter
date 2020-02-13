package base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ResourceHandler
{
    private static final int available = 1;
    private static final int checked_out = 2;
    private static final int nonCirculating = 3;
    private static final int contained = 4;

    public static void main(String[] args) throws IOException {
        System.out.println(available +" "+checked_out+ " "+nonCirculating+" "+contained);
        String filename = "";
        ArrayList<Resource> test = InCirculation(readResourceCSVFile(filename));
        for(Resource r: test)
        {
           System.out.println(r.toString());
        }
    }

    public static ArrayList<Resource> readResourceCSVFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Resource> result = new ArrayList<>();

        boolean first = true;
        while(true)
        {
            String line = br.readLine();
            if (line == null)
                break;
            String[] rea = line.split("\",\"");
            try {
                result.add(new Resource(rea[0], rea[1], rea[2], rea[3]));
            }
            catch (IndexOutOfBoundsException e)
            {
                //System.out.println("Invalid Input");
            }
        }
        return result;
    }

    public static ArrayList<Resource> InCirculation(ArrayList<Resource> input)
    {
        return filter(input, x -> (x.status_Id != nonCirculating && x.status_Id != contained));
    }

    public static <I> ArrayList<I> filter(ArrayList<I> input, Predicate<I> check)
    {
        ArrayList<I> result = new ArrayList<>();
        for(I x: input)
        {
            if(check.test(x))
            {
                result.add(x);
            }
        }
        return result;
    }


}
