package org.freakz.hokan_ng_springboot.bot.util;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.exception.HokanException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CmdExecutor {

  private List<String> output;

  public CmdExecutor(String cmd, String inputEncoding) throws HokanException {

    Process p;
    BufferedReader br;
    output = new ArrayList<>();

    try {

      p = Runtime.getRuntime().exec(cmd);
      int ret = p.waitFor();
      log.info("Process {} ended: {}", p, ret);
      br = new BufferedReader(new InputStreamReader(p.getInputStream(), inputEncoding));

      String l;
      do {
        l = br.readLine();
        if (l != null) {
          output.add(l);
        }

      } while (l != null);
      p.destroy();

    } catch (Exception e) {
      throw new HokanException(e);
    }
  }

  public String[] getOutput() {
    return output.toArray(new String[output.size()]);
  }

}
