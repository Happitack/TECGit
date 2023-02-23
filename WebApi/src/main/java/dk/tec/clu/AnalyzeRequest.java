package dk.tec.clu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnalyzeRequest 
{
	private MatchRequest matchLevel;
	private int personID;

	public MatchRequest getMatch() 
	{ 
		return matchLevel; 
	}
	
	public int getId() 
	{ 
		return personID; 
	}

	public AnalyzeRequest(String pathInfo) 
	{
		Matcher matcher = Pattern.compile("/Person/([0-9]+)").matcher(pathInfo); // + 1-N tal
		
		if(matcher.find())
		{
			matchLevel = MatchRequest.PersonId;
			personID = Integer.parseInt(matcher.group(1));
		}
		else 
		{
			matcher = Pattern.compile("/Person").matcher(pathInfo);
			if(matcher.find())
			{
				matchLevel = MatchRequest.Person;
			}
			else
			{
				matchLevel = MatchRequest.NoMatch;
			}
		}
	}
}