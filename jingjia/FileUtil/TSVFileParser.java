package FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Administrator on 2017/1/13.
 */
public class TSVFileParser {
    private static int _defaultColumnSize = 1000;
    private BufferedReader _fileStream = null;
    private boolean _inited = false;
    private String _spliter;
    private Hashtable<String, Integer> _columnMapping;
    public TSVFileParser(BufferedReader stream, String spliter) throws IOException{
        String header = stream.readLine();
        if(header == null){
            return;
        }
        _columnMapping = new Hashtable<String, Integer>(_defaultColumnSize);
        _fileStream = stream;
        _spliter = spliter;
        String[] columnNames = header.split(_spliter);
        for(int i = 0; i < columnNames.length; i++)
        {
            _columnMapping.put(columnNames[i],i);
        }
        _inited = true;
    }

    public TSVLine ReadLine() throws IOException{
        if(!_inited){
            return null;
        }
        String textLine = _fileStream.readLine();
        if(textLine == null){
            return null;
        }
        return new TSVLine(textLine, _spliter, _columnMapping);
    }
}
