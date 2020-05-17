package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CommunicationChannel;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author dvasjuta
 */
public interface CommunicationChannelReader  extends CanvasReader<CommunicationChannel, CommunicationChannelReader> {
    
    /**
     * Return list of all communication channels for a user.
     * @param userId id of user.
     * @return List of communication channels for user.
     * @throws IOException When there is an error communicating with Canvas
     */
    List<CommunicationChannel> listUserCommunicationChannels(int userId) throws IOException;
}
