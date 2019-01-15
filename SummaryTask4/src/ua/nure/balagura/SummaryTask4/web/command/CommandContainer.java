package ua.nure.balagura.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.web.command.act.AutoActionsCommand;
import ua.nure.balagura.SummaryTask4.web.command.act.FlightActionCommand;
import ua.nure.balagura.SummaryTask4.web.command.act.RequestRegistrationCommand;
import ua.nure.balagura.SummaryTask4.web.command.act.UserActionCommand;
import ua.nure.balagura.SummaryTask4.web.command.list.ListAutosCommand;
import ua.nure.balagura.SummaryTask4.web.command.list.ListFlightsCommand;
import ua.nure.balagura.SummaryTask4.web.command.list.ListRequestCommand;
import ua.nure.balagura.SummaryTask4.web.command.list.ListUsersCommand;
import ua.nure.balagura.SummaryTask4.web.command.requestActions.ApplyRequestCommand;
import ua.nure.balagura.SummaryTask4.web.command.requestActions.CancelRequestCommand;
import ua.nure.balagura.SummaryTask4.web.command.requestActions.CloseRequestCommand;

public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		
		commands.put("listUsers", new ListUsersCommand());
		commands.put("listAutos", new ListAutosCommand());
		commands.put("listFlights", new ListFlightsCommand());
		commands.put("listRequest", new ListRequestCommand());

		commands.put("actAut", new AutoActionsCommand());
		commands.put("actFlight", new FlightActionCommand());
		commands.put("actUser", new UserActionCommand());
		commands.put("regRequest", new RequestRegistrationCommand());
		commands.put("submitFlight", new SubmitFlightCommand());

		commands.put("applyRequest", new ApplyRequestCommand());
		commands.put("cancelRequest", new CancelRequestCommand());
		commands.put("closeRequest", new CloseRequestCommand());
		////////////////////////////////////////////////////
		commands.put("stat", new StatCommand());
		////////////////////////////////////////////////////
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}
	
	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * 
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}
}