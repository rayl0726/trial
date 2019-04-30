package experiment.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author : liulei
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        String eventType = null;

        switch(event.state()) {
//            case READER_IDLE:
//                eventType = "读空闲";
//                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                break;
            case ALL_IDLE:
                eventType = "全部空闲";
                break;
                default:
                    eventType = "error";
        }
        System.out.println(ctx.channel().remoteAddress() + "超时事件:" + eventType);
        ctx.channel().close();
    }

}
