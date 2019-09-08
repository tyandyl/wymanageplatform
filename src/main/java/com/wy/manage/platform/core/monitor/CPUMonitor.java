package com.wy.manage.platform.core.monitor;

import java.lang.management.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianye13 on 2019/4/23.
 */
public class CPUMonitor {
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    //Java 虚拟机的运行时系统的管理接口。使用它可以获取正在运行的 Java 虚拟机等信息，包括获取PID。
    private static RuntimeMXBean         rmBean     = ManagementFactory.getRuntimeMXBean();
    // 用于 Java 虚拟机的编译系统的管理接口。
    private static CompilationMXBean     cmpMBean   =ManagementFactory.getCompilationMXBean();
    private static ThreadMXBean          tmBean     = ManagementFactory.getThreadMXBean();
    private static MemoryMXBean          memoryBean = ManagementFactory.getMemoryMXBean();
    private static ClassLoadingMXBean    clMBean    = ManagementFactory.getClassLoadingMXBean();
    private static OperatingSystemMXBean osMBean    = ManagementFactory.getOperatingSystemMXBean();

    private static long upTime = -1L;
    private static long processCpuTime = -1L;
    private static long timeStamp;
    private static int nCPUs;

    private static long prevUpTime;
    private static long prevProcessCpuTime;

    static {
        if(upTime<0){
            //返回 Java 虚拟机的正常运行时间（以毫秒为单位）
            upTime = rmBean.getUptime();
        }
        if(processCpuTime<0){
            //getProcessCPUTime()返回自JVM进程创建以来使用的CPU时间。就这个数据本身而言，对于这儿并没有太多的用处。
            // 我需要更有用的Java方法来记录不同的时刻的数据快照（data snapshots），并报告任何两个时间点之间CPU的使用。
            processCpuTime =  ((com.sun.management.OperatingSystemMXBean) osMBean).getProcessCpuTime();
        }
        //返回 Java 虚拟机可以使用的处理器数目。此方法等效于 Runtime.availableProcessors() 方法
        nCPUs = osMBean.getAvailableProcessors();
        timeStamp = System.currentTimeMillis();
    }


    public void start(long period, TimeUnit unit) {
        this.executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (upTime > 0L && processCpuTime >= 0L) {
                    updateCPUInfo();
                }

            }
        }, period, period, unit);
    }

    public void updateCPUInfo() {
        upTime = rmBean.getUptime();
        processCpuTime =  ((com.sun.management.OperatingSystemMXBean) osMBean).getProcessCpuTime();
        if(prevUpTime > 0L && upTime > prevUpTime){
            long elapsedCpu = processCpuTime - prevProcessCpuTime;
            long elapsedTime = upTime - prevUpTime;
            float cpuUsage =
                    Math.min(99F,
                            elapsedCpu / (elapsedTime * 10000F * nCPUs));
            if(cpuUsage>0){
                System.out.println("cpu利用率："+cpuUsage+" ,cpu核数是："+nCPUs);
            }
            if(cpuUsage>40){
                long[] allThreadIds = tmBean.getAllThreadIds();
                if(allThreadIds!=null && allThreadIds.length>0){
                    for(int i=0;i<allThreadIds.length;i++){
                        ThreadInfo threadInfo = tmBean.getThreadInfo(allThreadIds[i]);
                        long threadCpuTime = tmBean.getThreadCpuTime(allThreadIds[i]);
                        if(threadCpuTime>0){
                            System.out.println("线程名称是:"+threadInfo.getThreadName()+" ,线程执行时间是："+threadCpuTime);
                        }
                        StackTraceElement[] stackTrace = threadInfo.getStackTrace();
                        if(stackTrace!=null){
                            for(StackTraceElement element:stackTrace){
                                System.out.println("栈堆类名:"+element.getClassName()+",方法是:"+element.getMethodName()+",行数是:"+element.getLineNumber());
                            }
                        }

                    }
                }
            }

        }
        prevUpTime=upTime;
        prevProcessCpuTime=processCpuTime;
    }


}
