/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CommunicationChannelReader;
import edu.ksu.canvas.interfaces.CommunicationChannelWriter;
import edu.ksu.canvas.model.CommunicationChannel;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.log4j.Logger;

/**
 *
 * @author dvasjuta
 */
public class CommunicationChannelImpl extends BaseImpl<CommunicationChannel, CommunicationChannelReader, CommunicationChannelWriter> implements CommunicationChannelReader,
    CommunicationChannelWriter {

    private static final Logger LOG = Logger.getLogger(CommunicationChannelReader.class);

    public CommunicationChannelImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
            paginationPageSize, serializeNulls);
    }

    @Override
    protected Class<CommunicationChannel> objectType() {
        return CommunicationChannel.class;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<CommunicationChannel>>() {
        }.getType();
    }

    
    @Override
    public List<CommunicationChannel> listUserCommunicationChannels(int userId) throws IOException {
        LOG.debug("Looking up communication channels for user " + userId);
        String url = buildCanvasUrl(String.format("users/%s//communication_channels", userId), Collections.emptyMap());
        return getListFromCanvas(url);
    }
}
