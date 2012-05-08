package org.cloud4fun.myjobs.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface MyJobsService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
}
