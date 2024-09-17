/*
 * Name: Taha Yar Khan
 * Student ID: 501150770
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{

		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int fromIndex = 0;
				int toIndex = 0;

				System.out.print("From store Content #: ");
				if (scanner.hasNextInt())
				{
					fromIndex = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt())
				{
					toIndex = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
			
				int range = toIndex-fromIndex;
				for(int x = 0; x<= range;x++){
				try {
						AudioContent content = store.getContent(fromIndex+x);
						mylibrary.download(content);
					
					
				} catch (AudioContentExistsException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				catch (AudioContentNotFoundException e)	{
					System.out.println(e.getMessage());
				}
			}
									
			}
			else if (action.equalsIgnoreCase("DOWNLOADA")) {
				System.out.println("Artist: ");
				String artist = scanner.next();

			}
			else if (action.equalsIgnoreCase("DOWNLOADA")) {

			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Song Number: ");
				int index = in.nextInt();
				try {
					mylibrary.playSong(index);
				} catch (AudioContentNotFoundException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				} 
				
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Audio Book Number: ");
				try {
					mylibrary.printAudioBookTOC(in.nextInt());

				} catch (AudioContentNotFoundException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}


			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Audio Book Number: ");
				int num = in.nextInt();
				System.out.print("Chapter: ");
				int chapter = in.nextInt();
				try {
					mylibrary.playAudioBook(num, chapter);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Playlist Title: ");
				String title = in.nextLine();
				mylibrary.playPlaylist(title);
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Playlist Title: ");
				String title = in.nextLine();
				System.out.print("Content Number: ");
				int index = in.nextInt();
				mylibrary.playPlaylist(title,index);
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Library Song: ");
				int index = in.nextInt();
				mylibrary.deleteSong(index);
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Playlist Title: ");
				mylibrary.makePlaylist(in.nextLine());
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				Scanner in =new Scanner(System.in);
				System.out.print("Playlist Title: ");
				String title = in.nextLine();
				mylibrary.printPlaylist(title);
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Playlist Title: ");
				String title = in.nextLine();
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				String type = in.nextLine();
				System.out.print("Library Content #: ");
				int content = in.nextInt();
				mylibrary.addContentToPlaylist(type, content, title);
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				Scanner in = new Scanner(System.in);
				System.out.print("Playlist Title: ");
				String title = in.nextLine();
				System.out.print("Playlist Content #: ");
				int index = in.nextInt();
				mylibrary.delContentFromPlaylist(index, title);
				
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			//new added actions
			else if (action.equalsIgnoreCase("SEARCH")){ //search store for audio content with specified title, prints index and info
				Scanner in =new Scanner(System.in);
				System.out.print("Title: ");
				String title = in.nextLine();
				try {
					store.getContentT(title);
				} catch (AudioContentNotFoundException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHA")){ //search store for audio content with specified artist name, prints index/indices and info 
				Scanner in =new Scanner(System.in);
				System.out.print("Artist: ");
				String artist = in.nextLine();
				try {
					store.getContentA(artist);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHG")){ //search store for audio content with specified genre , prints index/indices and info 
				Scanner in =new Scanner(System.in);
				System.out.print("Genre: [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				String genre = in.nextLine();
				try {
					store.getContentG(genre);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("DOWNLOADA")){ //takes artist name and downloads all audiocontent from that artist/author
				
			}
			else if (action.equalsIgnoreCase("DOWNLOADG")){ //tkaes genre and downloads all audiocontent of that genre
				
			}




			System.out.print("\n>");
		}
	}
}
