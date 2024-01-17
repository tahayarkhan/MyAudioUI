/*
 * Name: Stephen Tao
 * Student ID: 501189625
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
import javax.sound.sampled.AudioPermission;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; //array list to hold all audio contents 
		private Map<String, Integer> searchTitle;
		private Map<String, ArrayList<Integer>> searchArtist;
		private Map<String, ArrayList<Integer>> searchGenre;


		public AudioContentStore()
		{
			try {
				contents = createContent("store.txt"); //add all contents from file
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println(e.getMessage()); //handle IOException
				System.exit(1);
			}

			//create the maps to search for content
			searchTitle = new HashMap<String, Integer>();	
			for(int x =0;x<contents.size();x++){
				searchTitle.put(contents.get(x).getTitle(), x+1);
			}

			searchArtist = new HashMap<String, ArrayList<Integer>>();

		
			for(int x =0;x<contents.size();x++){
				if(contents.get(x).getType().equals("SONG")){
					Song s = (Song)contents.get(x);
					if(searchArtist.containsKey(s.getArtist())){
						searchArtist.get(s.getArtist()).add(x+1);	
					}
					else{
						ArrayList<Integer> idx = new ArrayList<Integer>();						
						idx.add(x+1);
						searchArtist.put(s.getArtist(),idx);
					}

				}
					
				
				if(contents.get(x).getType().equals("AUDIOBOOK")){
					AudioBook book = (AudioBook)contents.get(x);
					if(searchArtist.containsKey(book.getAuthor())){
						searchArtist.get(book.getAuthor()).add(x+1);	
					}
					else{
						ArrayList<Integer> idx = new ArrayList<Integer>();						
						idx.add(x+1);
						searchArtist.put(book.getAuthor(),idx);
					}

				}
			}
			
			
			searchGenre = new HashMap<String, ArrayList<Integer>>();			
			for(int x =0;x<contents.size();x++){
				if(contents.get(x).getType().equals("SONG")){
					Song s = (Song)contents.get(x);
					if(searchGenre.containsKey(s.getGenre().name())){
						searchGenre.get(s.getGenre().name()).add(x+1);	
					}
					else{
						ArrayList<Integer> idx = new ArrayList<Integer>();						
						idx.add(x+1);
						searchGenre.put(s.getGenre().name(),idx);
					}

				}
			}
				
			
		}
		


		public AudioContent getContent(int index) throws AudioContentNotFoundException
		{
			if (index < 1 || index > contents.size())
			{
				throw new AudioContentNotFoundException("Content not found");
			}
			return contents.get(index-1);
		}
		


		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}

		//get content based on content name
		public void getContentT(String title) throws AudioContentNotFoundException 
		{
			if(searchTitle.get(title)==null){
				throw new AudioContentNotFoundException("No matches for " + title);
			}
			System.out.print(searchTitle.get(title) + ". ");
			contents.get(searchTitle.get(title)-1).printInfo(); 
		}

		//get content based on artist name
		public void getContentA(String artistName) throws AudioContentNotFoundException 
		 {  
			if(searchArtist.get(artistName)==null){
				throw new AudioContentNotFoundException("No matches for " + artistName);
			}
			for(int x = 0; x< searchArtist.get(artistName).size();x++){
				System.out.print(searchArtist.get(artistName).get(x) + ". ");
				contents.get(searchArtist.get(artistName).get(x)-1).printInfo();
				System.out.println();
			}
		}

		 //get content based on genre
		public void getContentG(String genre) throws AudioContentNotFoundException
		{
			if(searchGenre.get(genre)==null){
				throw new AudioContentNotFoundException("No matches for " + genre);
			}
			
			for(int x = 0; x< searchGenre.get(genre).size();x++){
				System.out.print(searchGenre.get(genre).get(x) + ". ");
				contents.get(searchGenre.get(genre).get(x)-1).printInfo();
				System.out.println();
			}
		}	
		
		//adds all content from the file 
		private ArrayList<AudioContent> createContent(String filename) throws IOException{
			contents = new ArrayList<AudioContent>();

			Scanner in = new Scanner(new FileReader(filename));
			while(in.hasNextLine()){
				if(in.nextLine().equals("SONG")){ //find all SONG audiocontent and extract info, add to arr list
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());
					String artist = in.nextLine();
					String composer = in.nextLine();
					String genre = in.nextLine();
					Song.Genre g = Song.Genre.valueOf(genre);
					int numLyrics = Integer.parseInt(in.nextLine());
					String lyrics = "";
					for(int x = 0;x<numLyrics;x++){ //loop through # of lines that contains lyrics
						 lyrics += in.nextLine() + "\n";
					}
					contents.add(new Song(title, year, id, "SONG", lyrics, length, artist, composer, g, lyrics));

				}
			}


			Scanner in2 = new Scanner(new FileReader(filename));
			while(in2.hasNextLine()){
				if(in2.nextLine().equals("AUDIOBOOK")){ //find all AUDIOBOOK audiocontent and extract info, add to arr list
					String id = in2.nextLine();
					String title = in2.nextLine();
					int year = Integer.parseInt(in2.nextLine());
					int length = Integer.parseInt(in2.nextLine());
					String author = in2.nextLine();
					String narrator = in2.nextLine();
					int numChaptors = Integer.parseInt(in2.nextLine());
					ArrayList<String> chapterTitles = new ArrayList<String>(); //chapter titles array list
					for(int x = 0;x<numChaptors;x++){ //loop through # of lines that contain chapter titles
						chapterTitles.add(in2.nextLine());
					}
					int numLines = Integer.parseInt(in2.nextLine());
					ArrayList<String> chapterLines = new ArrayList<String>(); 
					for(int y = 0;y<numLines; y++){//loop through # of lines that contain chapter lines
						chapterLines.add(in2.nextLine()+ "\n");
					} 
					contents.add(new AudioBook(title, year, id, "AUDIOBOOK",  "", length,author, narrator, chapterTitles, chapterLines));
				}
			}
			
			return contents;
			
		}	
}
