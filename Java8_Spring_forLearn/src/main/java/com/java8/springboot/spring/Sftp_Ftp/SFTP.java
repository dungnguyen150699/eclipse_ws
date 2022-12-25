package com.java8.springboot.spring.Sftp_Ftp;
// Chưa có server để test cứ từ từ nhé 

import com.jcraft.jsch.*;

public class SFTP {

	private static String remoteHost = "192.168.0.101";
	private static String username = "sftpuser";
	private static String password = "123";
	private static Integer port = 22;

	public static ChannelSftp setupJsch() throws JSchException {
		JSch jsch = new JSch();
//	    jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
		Session jschSession = jsch.getSession(username, remoteHost, 22);
		jschSession.setPassword(password);
		jschSession.setConfig("StrictHostKeyChecking", "no");
		jschSession.setPassword(password);
		jschSession.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}

	public static void whenUploadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
		ChannelSftp channelSftp = setupJsch();
		channelSftp.connect();
		String localFile = "/logs1/username.txt";
		String remoteDir = "remote_sftp_test/";

		channelSftp.put(localFile, remoteDir + "jschFile.txt");
		channelSftp.exit();
	}

/*  Xem ở đây nhé
 * https://stackoverflow.com/questions/21227330/difference-b-w-channelsftps-lstat-and-stat-method-in-jsch
 */
	public boolean checkExistFolder(ChannelSftp channel, String path) {
		try {
			channel.lstat(path);
			return true;
		} catch (SftpException ex) {
			return false;
		}
	}
	
	public boolean createFolder(ChannelSftp channel, String path) throws Exception {
        try {
            channel.mkdir(path);
            return true;
        } catch (SftpException ex) {
//            log.error(ex.getMessage(), ex);
            throw new Exception(ex.getMessage(), ex);
        }
    }

	public static void whenDownloadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
		ChannelSftp channelSftp = setupJsch();
		channelSftp.connect();

		String remoteFile = "jschFile.txt";
		String remoteDir = "remote_sftp_test/";
		String localDir = "/logs1/";

		channelSftp.get(remoteDir + remoteFile, localDir + "jschFile1.txt");
	    channelSftp.get(remoteFile);

		channelSftp.exit();
	}

	// Nhận đc InputStream sau đó đọc file

	public static void main(String[] args) throws JSchException, SftpException {
//		whenUploadFileUsingJsch_thenSuccess();
		whenDownloadFileUsingJsch_thenSuccess();
	}
}
