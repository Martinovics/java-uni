package lab04.prog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;

import lab04.classes.*;




public class Main
{


    private static ArrayList<Beer> beers;




    public static void main(String[] args)
    {
        Beer beer1 = new Beer("name1", "style1", 5.5);
        Beer beer2 = new Beer("name2", "style2", 7.2);

        System.out.println(beer1);
        System.out.println(beer2);


        System.out.println("Enter something:");
        System.out.print(">>> ");

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] spLine = line.split(" ");

        System.out.println("entered: " + spLine[0] + " | length: " + spLine.length + "\n");


        beers = new ArrayList<Beer>();

        System.out.println("Available commands: (add, list, save, load)");

        while (true)
        {
            System.out.print(">>> ");

            line = sc.nextLine();
            spLine = line.split(" ");

            if (spLine[0].equals("exit"))
            {
                break;
            }

            else if (spLine[0].equals("add"))
            {
                add(spLine);
            }
            else if (spLine[0].equals("list"))
            {
                list(spLine);
            }
            else if (spLine[0].equals("search"))
            {
                search(spLine);
            }
            else if (spLine[0].equals("find"))
            {
                find(spLine);
            }
            else if (spLine[0].equals("delete"))
            {
                delete(spLine);
            }
            else if (spLine[0].equals("save"))
            {
                try {
                    save(spLine);
                } catch (IOException e) {
                    System.out.println("Something went wrong:/");
                }
            }
            else if (spLine[0].equals("load"))
            {
                try {
                    load(spLine);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Something went wrong:/");
                }
            }

        }

        sc.close();

    }




    protected static void add(String[] cmd)
    {
        beers.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
    }




    protected static void list(String[] cmd)
    {
        if (cmd.length == 2)
        {
            if (cmd[1].equals("name"))
            {
                Collections.sort(beers, new NameComparator());
            }
            else if (cmd[1].equals("style"))
            {
                Collections.sort(beers, new StyleComparator());
            }
            else if (cmd[1].equals("strength"))
            {
                Collections.sort(beers, new StrengthComparator());
            }
        }

        for (Beer beer : beers)
        {
            System.out.println(beer.getName() + " " + beer.getStyle() + " " + beer.getStrength());
        }

    }




    protected static void search(String[] cmd)
    {
        for (Beer beer : beers)
        {
            if (beer.getName().toLowerCase().equals(cmd[1].toLowerCase()))
            {
                System.out.println(beer.getName() + " " + beer.getStyle() + " " + beer.getStrength());
            }
        }
    }




    protected static void find(String[] cmd)
    {
        for (Beer beer : beers)
        {
            if (beer.getName().toLowerCase().contains(cmd[1].toLowerCase()))
            {
                System.out.println(beer.getName() + " " + beer.getStyle() + " " + beer.getStrength());
            }
        }
    }




    protected static void delete(String[] cmd)
    {
        Iterator<Beer> it = beers.iterator();
        while(it.hasNext()){

            Beer beer = it.next();
            if (cmd.length == 2)
            {
                if (beer.getName().toLowerCase().equals(cmd[1].toLowerCase()))
                {
                    it.remove();
                }
            }
            else if (cmd.length == 3)
            {
                if (cmd[1].equals("style"))
                {
                    if (beer.getStyle().toLowerCase().equals(cmd[2].toLowerCase()))
                    {
                        it.remove();
                    }
                }
                else if (cmd[1].equals("strength"))
                {
                    if (beer.getStrength() == Double.parseDouble(cmd[2]))
                    {
                        it.remove();
                    }
                }
            }


        }
    }




    protected static void save(String[] cmd) throws IOException
    {
        FileOutputStream fo = new FileOutputStream("./files/" + cmd[1] + ".ser");
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(beers);
        so.close();

        System.out.println("Beers have been saved!");
    }



    @SuppressWarnings (value="unchecked")
    protected static void load(String[] cmd) throws IOException, ClassNotFoundException
    {
        FileInputStream fi = new FileInputStream("./files/" + cmd[1] + ".ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        beers = (ArrayList<Beer>) oi.readObject();
        oi.close();
    }




}
