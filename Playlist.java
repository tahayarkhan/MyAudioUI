/*
 * Name: Taha Yar Khan
 * Student ID: 501150770

 */
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents() //DONE!!!
	{
		for(int i = 0;i<contents.size();i++){
			System.out.print(i+1+". ");
			if(contents.get(i).getType().equals("SONG")){
				contents.get(i).printInfo();
				System.out.println();
			}
			if(contents.get(i).getType().equals("AUDIOBOOK")){
				contents.get(i).printInfo();
				System.out.println();
			}
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll() //NEED TO FIX
	{
		for(AudioContent a:contents){
			a.play();
			System.out.println();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index) 
	{
		System.out.println(contents.get(index-1).getTitle()); //print title of playlist
		contents.get(index-1).play(); //print the content of given audiocontent
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)  
	{
		Playlist p = (Playlist)other;
		if(this.title.equals(p.title)){
			return true;
		}
		else return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index) 
	{
		if(this.contains(index)){ //check if index is valid
			contents.remove(index-1); //remove the given index audiocontent
		
		}
	}
	
	
}
