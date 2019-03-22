package com.hankcs.lucene;

import com.hankcs.cfg.Configuration;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * CRF分析器
 *
 * @author Kenn
 */
public class HanLPCRFAnalyzer extends Analyzer {

    private Configuration configuration;

    public HanLPCRFAnalyzer(Configuration configuration) {
        this.configuration = configuration;
    }

    public HanLPCRFAnalyzer() {
        super();
    }

    protected Analyzer.TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = AccessController.doPrivileged((PrivilegedAction<HanLPTokenizer>) () -> {
            try {
                return new HanLPTokenizer(new CRFLexicalAnalyzer(), configuration);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
        return new Analyzer.TokenStreamComponents(tokenizer);
    }
}
