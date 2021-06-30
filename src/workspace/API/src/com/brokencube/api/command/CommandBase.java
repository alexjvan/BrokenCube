package com.brokencube.api.command;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public abstract class CommandBase {
	public String description = "No description set";
	public String useCase = "No use case set";
	public List<String> alternatives = new ArrayList<>();
	
	public void addAlternative(String useCase) {
		this.alternatives.add(useCase);
	}
	
	public String getAlternative(int index) {
		return this.alternatives.get(index);
	}
	
	public List<String> getAlternatives() {
		return this.alternatives;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getUseCase() {
		return this.useCase;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setUseCase(String useCase) {
		this.useCase = useCase;
	}
	
	// Execution
	public void userExe(User u, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		exe(u, split);
	}
	public void consoleExe(Console c, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoConsoleImplementationException, NoPermsException {
		exe(c, split);
	}
	public abstract void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException;

	public String[] getFlag(String[] split, String flag) {
		int startIndex = 0;
		int length = 0;
		// Check for flags
		for(int i = 1; i < split.length; i++) {
			if(split[i].startsWith("-")) {
				if(startIndex != 0)
					break;
				else {
					if(split[i].substring(1).equals(flag))
						startIndex = i + 1;
				}
			} else if(startIndex != 0) {
				length++;
			}
		}
		
		if(startIndex == 0)
			return null;
		
		// Build the returnable
		String[] returnable = new String[length];
		for(int i = 0; i < length; i++)
			returnable[i] = split[i + startIndex];
		
		return returnable;
	}
	
}
