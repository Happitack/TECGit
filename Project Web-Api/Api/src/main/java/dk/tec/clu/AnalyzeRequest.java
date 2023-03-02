package dk.tec.clu;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequest 
{
	MatchRequest level;
	int id;
	
	public MatchRequest getLevel() {
		return level;
	}

	public int getId() {
		return id;
	}
	
	public AnalyzeRequest(String pathInfo)
	{
		Matcher matcher = Pattern.compile("/Person/([0-9]+)").matcher(pathInfo);
		
		if(matcher.find())
		{
			level = MatchRequest.PersonId;
			id = Integer.parseInt(matcher.group(1));
		}
		else
		{
			matcher = Pattern.compile("/Elev/").matcher(pathInfo);
			if(matcher.find())
			{
				level = MatchRequest.Person;
			}
			else {
				level = MatchRequest.NoMatch;
			}
		}	
	}
}