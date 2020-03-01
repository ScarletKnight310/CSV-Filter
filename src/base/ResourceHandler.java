package base;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ResourceHandler
{
    private static final int available = 1;
    private static final int checked_out = 2;
    private static final int nonCirculating = 3;
    private static final int contained = 4;

    private ArrayList<Resource> full_list;
    private ArrayList<Resource> edited_list;

    public ResourceHandler(String filename){
        try
        {
            this.full_list = readResourceCSVFile(filename);
            edited_list = full_list;
        }
        catch(IOException e)
        {
        }
    }

    public ArrayList<Resource> readResourceCSVFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Resource> result = new ArrayList<>();

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

    public void InCirculationAndNeedsReplacement(ArrayList<Resource> input)
    {
        filter(full_list, edited_list, x -> (x.status_Id != nonCirculating && x.status_Id != contained && x.isOld()));
    }

    public void InCirculation(ArrayList<Resource> input)
    {
        filter(full_list, edited_list, x -> (x.status_Id != nonCirculating && x.status_Id != contained));
    }

    public void NeedsReplacement()
    {
        filter(full_list, edited_list, x -> (x.isOld() && x.status_Id != contained));
    }

    public <I> void filter(ArrayList<I> original, ArrayList<I> new_list, Predicate<I> check)
    {
        ArrayList<I> result = new ArrayList<>();
        for(I x: original)
        {
            if(check.test(x))
            {
                new_list.add(x);
            }
        }
    }


    public ArrayList<Resource> getFullResourceList()
    {
        return full_list;
    }

    public ArrayList<Resource> getEditedResourceList()
    {
        return edited_list;
    }

//    public void SaveFile()
//    {
//        File
//    }

}
