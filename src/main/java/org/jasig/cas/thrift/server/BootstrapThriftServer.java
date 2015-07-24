package org.jasig.cas.thrift.server;

import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.jasig.cas.thrift.server.UserServiceThrift.Processor;

/**
 * @author dongtian
 * @date   2015年5月28日 上午9:18:09
 */
public class BootstrapThriftServer {

	private UserServiceImplThrift userServiceImplThrift;
	private int port;

	public BootstrapThriftServer(UserServiceImplThrift userServiceImplThrift,
			int port) {
		super();
		this.userServiceImplThrift = userServiceImplThrift;
		this.port = port;
	}


	private TThreadedSelectorServer tThreadedSelectorServer ;
	public void startServe() {
		
		System.err.println("正在启动thrift服务.......");
		new Thread(){

			@Override
			public void run() {
				//TServerTransport serverSocket =  new TServerSocket(9999);
				UserServiceThrift.Processor<UserServiceThrift.Iface> processor = new Processor<UserServiceThrift.Iface>(userServiceImplThrift); 
				TNonblockingServerTransport nonblockingServerTransport;
				try {
					nonblockingServerTransport = new TNonblockingServerSocket(port);
					TThreadedSelectorServer.Args args3 = new TThreadedSelectorServer.Args(nonblockingServerTransport);
					args3.processor(processor);
					tThreadedSelectorServer = new TThreadedSelectorServer(args3);
					tThreadedSelectorServer.serve();
				} catch (TTransportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}.start();
	
		System.err.println("thrift服务已经启动....");
	}

	
	public void stopServe () {
		
		if(tThreadedSelectorServer!= null) {
			tThreadedSelectorServer.stop();
		}
	}
}
