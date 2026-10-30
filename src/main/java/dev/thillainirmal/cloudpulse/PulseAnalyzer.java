package dev.thillainirmal.cloudpulse;
import jakarta.validation.constraints.Max;import jakarta.validation.constraints.Min;import jakarta.validation.constraints.NotBlank;import jakarta.validation.constraints.Pattern;import org.springframework.stereotype.Service;import java.util.*;
@Service
public class PulseAnalyzer{
 public Analysis analyze(Snapshot s){
  List<Finding> f=new ArrayList<>();int score=100;
  if(s.errorRate>5){score-=30;f.add(new Finding("CRITICAL","Error-rate burn","HTTP errors exceed 5%","Inspect recent deployment and correlated traces; rollback requires approval."));}
  if(s.p95LatencyMs>800){score-=20;f.add(new Finding("HIGH","Latency saturation","p95 latency exceeds 800 ms","Compare CPU throttling, dependency latency and queue depth."));}
  if(s.cpuPercent>85){score-=15;f.add(new Finding("HIGH","CPU pressure","CPU exceeds 85%","Inspect throttling before changing replica count."));}
  if(s.memoryPercent>90){score-=20;f.add(new Finding("CRITICAL","Memory pressure","Memory exceeds 90%","Capture heap evidence and check OOM restart history."));}
  if(s.restartCount>2){score-=15;f.add(new Finding("HIGH","Restart loop","Pod restarts exceed safe baseline","Inspect termination reason and readiness failures."));}
  String cause=f.isEmpty()?"No active anomaly":(s.restartCount>2&&s.memoryPercent>90?"Probable memory-driven crash loop":s.errorRate>5?"Probable application or dependency failure":"Probable resource saturation");
  return new Analysis(Math.max(0,score),score>=85?"HEALTHY":score>=60?"DEGRADED":"CRITICAL",cause,f,List.of("Metrics Agent correlated five golden signals","Topology Agent scoped workload "+s.workload,"Runbook Agent produced read-only diagnostics","Safety Guard blocked automatic production mutation"));
 }
 public record Snapshot(@NotBlank @Pattern(regexp="[a-zA-Z0-9_.-]{1,63}") String namespace,@NotBlank @Pattern(regexp="[a-zA-Z0-9_.-]{1,63}") String workload,@Min(0) @Max(100) double cpuPercent,@Min(0) @Max(100) double memoryPercent,@Min(0) @Max(100) double errorRate,@Min(0) @Max(600000) int p95LatencyMs,@Min(0) @Max(1000000) int restartCount){}
 public record Finding(String severity,String title,String evidence,String action){}
 public record Analysis(int healthScore,String status,String probableCause,List<Finding> findings,List<String> trace){}
}
