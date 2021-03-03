package com.brokencube.api.command;

import com.brokencube.api.API;
import com.brokencube.api.ranks.Rank;

public abstract class SubCommand extends CommandBase {
	protected API instance;

	private Command parent;

	public String permString;
	private Rank perm;

	public SubCommand(Command parent, String permission) {
		this.parent = parent;
		this.parent.addChild(this);

		this.instance = parent.instance;

		this.permString = permission;
		regrabPerm();
	}

	public void regrabPerm() {
		Rank prank = instance.getPR().getRank(this.permString);

		this.perm = prank;

		if (!prank.equals(instance.getRM().getRankFromName("Undefined")))
			if (parent.getLowestPermNeeded().equals(instance.getRM().getRankFromName("Undefined")) || parent.getLowestPermNeeded().num > prank.num)
				parent.lowestPermNeeded = prank;
	}

}
