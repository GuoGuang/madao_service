package com.madao.user.config;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class SocketIoRunner implements CommandLineRunner {
    private final SocketIOServer server;

    @Override
    public void run(String... args) throws Exception {
        server.start();
        log.info("socket.io启动成功，port:{}",server.getConfiguration().getPort());
    }
}
