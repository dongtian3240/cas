package org.jasig.cas;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.jasig.cas.thrift.server.UserServiceThrift;
import org.jasig.cas.thrift.server.UserServiceThrift.Processor;

public class ThriftTest {

	
	public static void main(String[] args) throws TTransportException {
		
		
		try {
			Class.forName("org.jasig.cas.thrift.server.UserServiceThrift$Processor");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		new Thread(){

			@Override
			public void run() {
				//TServerTransport serverSocket =  new TServerSocket(9999);
				UserServiceThrift.Processor<UserServiceThrift.Iface> processor = new Processor<UserServiceThrift.Iface>(null);
//				Args args2 = new Args(serverSocket);
//				args2.processor(processor);
//				TServer tServer = new TThreadPoolServer(args2);
//				System.err.println("正在启动 thrift服务  监听端口 9999");
//				tServer.serve();
				// 
				TNonblockingServerTransport nonblockingServerTransport;
				try {
					nonblockingServerTransport = new TNonblockingServerSocket(9999);
					TThreadedSelectorServer.Args args3 = new TThreadedSelectorServer.Args(nonblockingServerTransport);
					args3.processor(processor);
					TThreadedSelectorServer server = new TThreadedSelectorServer(args3);
					server.serve();
				} catch (TTransportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}.start();
	
		System.err.println(" server is run.........");
	}
}
