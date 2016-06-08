package nl.hu.fnt.gsos.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
@Path("/")
public class TrackServiceImpl {
	private List<Track> tracks = new ArrayList<Track>();

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public TrackServiceImpl() {
		tracks.add(new Track(1, "The Doors", "Light My Fire", 1967,
				"www.youtube.com/watch?v=M_yWyBjDEaU"));
		tracks.add(new Track(2, "Rolling Stones", "Angie", 1974,
				"www.youtube.com/watch?v=RcZn2-bGXqQ"));
		tracks.add(new Track(3, "Kraftwerk", "The model", 1978,
				"www.youtube.com/watch?v=BdZDhpkDziE"));
		tracks.add(new Track(4, "Pixies", "Monkey Gone to Heaven", 1989,
				"www.youtube.com/watch?v=mK3iSglbZUM"));
		tracks.add(new Track(5, "St germain", "Rose Rouge", 2000,
				"https://www.youtube.com/watch?v=yRpKKBmeqV4"));
		tracks.add(new Track(6, "Lilly Wood & The Prick",
				"Prayer In C (Robin Schulz Remix)", 2014,
				"https://www.youtube.com/watch?v=fiore9Z5iUg"));
	}
	@GET
	@Path("/tracks/{id}")
	@Produces("application/json")
	public Track getTrackById(@PathParam("id") int id) {
		for (Track track : tracks) {
			if (track.getId() == id) {
				return track;
			}
		}
		return null;
	}
	@GET
	@Path("/tracks")
	@Produces("application/json")
	public List<Track> getTracks() {
		for(Track t : tracks){
			System.out.println(t.id);
		}
		return tracks;
	}
	@DELETE
	@Path("/tracks/delete/{id}")
	@Produces("application/json")
	public boolean remove(@PathParam("id")int id) {
		System.out.println(tracks.size());
		boolean b = tracks.remove(findTrack(id));
		System.out.println(tracks.size());
		setTracks(tracks);
		return b;
	}
	
	private Track findTrack(int id) {
		for (Track track: tracks) {
			if (track.getId()==id) {
				return track;
			}
				
		}
		return null;
	}
	@PUT
	@Path("/tracks/put/{artist}/{name}/{year}/{link}")
	public void addTrack(@PathParam("artist") String artist,@PathParam("name")String name,@PathParam("year") int year,@PathParam("link") String link){
		int id = tracks.size()+1;
		Track t = new Track(id,artist,name,year,link);
		System.out.println(t.getId() + tracks.size());
		tracks.add(t);
		setTracks(tracks);
		System.out.println(tracks.size());
		System.out.println(t.getArtist() + t.getSong() + t.getYear() + t.getUrl());
	}

}
