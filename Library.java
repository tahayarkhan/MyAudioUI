/*
 * Name: Stephen Tao
 * Student ID: 501189625
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) throws AudioContentExistsException, AudioContentNotFoundException
	{
		if(content.getType().equals(Song.TYPENAME)){ //if the audiocontent is a song
			if(songs.contains((Song)content)){ //check if the song list already contains that song
				throw new AudioContentExistsException("Song" + " " + content.getTitle()+ " already downloaded"); //set error message
			}
			else{
				songs.add((Song)content); //if not in song list, add it to the list
				System.out.println(content.getType()+ " " + content.getTitle() + " Added to Library");
			}
		}
		if(content.getType().equals(AudioBook.TYPENAME)){ //if the audiocontent is an audiobook
			if(audiobooks.contains((AudioBook)content)){ //check if the audobook list already contains that audiobook
				throw new AudioContentExistsException("Audiobook" + " " + content.getTitle()+ " already downloaded");
			}
			else{
				audiobooks.add((AudioBook)content); //if not in audiobook list, add to the list
				System.out.println(content.getType()+ " " + content.getTitle() + " Added to Library");

			}
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() 
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() 
	{
		for(int i = 0; i< audiobooks.size();i++){ //loop through the list of audiobooks
			System.out.print(i+1 +". "); 
			audiobooks.get(i).printInfo(); //print out all the info of each book in the list
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for(int i = 0; i< playlists.size();i++){ //loop through all playlists
			System.out.print(i+1 +". "); //print the index num
			System.out.println(playlists.get(i).getTitle()); //print play last name
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists() 
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<String>(); //list to hold all the artist names
		for(Song s:songs){ //loop through all songs to get the artists
			if(!artists.contains(s.getArtist())){ //if they are not in the list, add them (so no duplicates)
				artists.add(s.getArtist());
			}
		}
		for(int i=0; i<artists.size();i++){ //loop through all artists added and print
			System.out.println(i+1 + ". " + artists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index) 
	{
		songs.remove(index-1);
		return false;
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs,new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b){ //override compare method
			if(a.getYear() == b.getYear()){
				return 0;
			}
			else if(a.getYear() > b.getYear()){
				return 1;
			}
			else return -1;
		}

	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
	 Collections.sort(songs,new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b){ //override compare method
			if(a.getLength() == b.getLength()){
				return 0;
			}
			else if(a.getLength() > b.getLength()){
				return 1;
			}
			else return -1;
		}
		
	}

	// Sort songs by title 
	public void sortSongsByName() 
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
		
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index) throws AudioContentNotFoundException
	{
		if (index < 1 || index > songs.size())
		{
			throw new AudioContentNotFoundException("Song not found");
		}
		songs.get(index-1).play();
	}
	
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException
	{
		if (index < 1 || index > audiobooks.size())
		{
			throw new AudioContentNotFoundException("Audiobook not found");
		}
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
		
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) throws AudioContentNotFoundException
	{
		if(index < 0 || index > audiobooks.size()){
			throw new AudioContentNotFoundException("Audiobook not found");
		}
		else audiobooks.get(index-1).printTOC();	

	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) 
	{
		Playlist p = new Playlist(title); //new playlist with given title
		if(playlists.contains(p)){ //check if that playlists exists
			throw new AudioContentExistsException(title +" already exists");
		}
		else{
			playlists.add(p); //add it to playlists list if it doesnt
		}
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title) 
	{
		for(Playlist p:playlists){ //loop through playlists list
			if(p.getTitle().equals(title)){ //check if the given title matches any in the list
				p.printContents(); //print the audiocontent info
			}
		}
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) 
	{
		for(Playlist p:playlists){
			if(p.getTitle().equals(playlistTitle)){
				p.playAll();
			}
		}
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) 
	{
		for(Playlist p:playlists){
			if(p.getTitle().equals(playlistTitle)){
				p.play(indexInPL);
			}
		}
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) 
	{
		for(Playlist p:playlists){ //loop through playlists 
			if(p.getTitle().equals(playlistTitle)){ //if there exists a playlist equal to the one user entered
				if(type.equalsIgnoreCase(Song.TYPENAME)){ //check if audiocontent is song
					p.addContent(songs.get(index-1)); //add song with given index to playlist 
				}
				if(type.equalsIgnoreCase(AudioBook.TYPENAME)){ //check if audiocontent is audiobook
					p.addContent(audiobooks.get(index-1)); //add audiobook with given index to playlist
				}
			}
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title) 
	{
		for(Playlist p:playlists){
			if(p.getTitle().equals(title)){
				if(!(index < 0 ||index>playlists.size())){ //if index is valid
					p.deleteContent(index);
				}
			}
		}
	}
	
}

//create excpetion classes

//exception when content not found
 class AudioContentNotFoundException extends RuntimeException{
	public AudioContentNotFoundException(){
	}
	public AudioContentNotFoundException(String message){
        super(message);
	}
}

//exception when content exists
class AudioContentExistsException extends RuntimeException{
	public AudioContentExistsException(){
	}
	public AudioContentExistsException(String message){
        super(message);
	}
}