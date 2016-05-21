package ass06.ex1;

import java.util.Optional;

public interface Blackboard {
	
	/**
	 * Puts a new message on the blakboard
	 * @param tag tag key of the message
	 * @param msg content 
	 */
	void post(String tag, Msg msg);	
	
	/**
	 * Removes a message with the specified tag from the blabkboard.
	 * It blocks if a message with the specified tag is not available,
	 * until one can be taken.
	 * 
	 * @param tag the key of the message to be removed
	 * @return
	 */
	Msg take(String tag);
	
	/**
	 * Removes a message with the specified tag from the blabkboard,
	 * if the message is available. If it is not, then it returns an empty optional,
	 * without blocking.
	 * 
	 * @param tag the key of the message to be removed
	 * @return
	 */
	Optional<Msg> takeIfPresent(String tag);

	
	/**
	 * Reads without removing a message with the specified tag from the blabkboard.
	 * It blocks if a message with the specified tag is not available,
	 * until one can be taken.
	 * 
	 * @param tag the key of the message to be removed
	 * @return
	 */
	Msg read(String tag);

	/**
	 * Reads without removing a message with the specified tag from the blabkboard,
	 * if the message is available. If it is not, then it returns an empty optional,
	 * without blocking.
	 * 
	 * @param tag the key of the message to be removed
	 * @return
	 */	
	Optional<Msg> readIfPresent(String tag);
	
	
}
