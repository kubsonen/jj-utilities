package pl.jj.util.generator;

import java.lang.reflect.Field;

/**
 * @author JNartowicz
 */
public class RandomConst {
    public static final String PREFIX = "s";
    private static final String[] s0 = {"ccc", "ccd", "ksv", "egs", "egw", "tel", "ktd", "iph", "cda", "teq", "viy", "ipp", "ehl", "tey", "zre", "ips", "glu", "zrk", "rbb", "zrm", "zro", "myf", "myh", "iqg", "eic", "vjr", "gmk", "eii", "gml", "cej", "gmu", "zsh", "iqv", "zsg", "vkc", "rce", "cey", "vkk", "vkm", "kve", "rcp", "zsz", "abc", "gnl", "gnm", "mzs", "kvt", "irq", "kvv", "abl", "ztd", "gnt", "vlb", "vle", "irw", "thi", "abu", "ztp", "aby", "ztw", "rdq", "acc", "vlv", "tht", "rdu", "goj", "rdy", "gon", "isr", "cgq", "tia", "cgt", "acv", "itb", "cgz", "ite", "tir", "zuy", "ret", "rev", "adh", "rew", "vnd", "chq", "gpu", "vni", "tji", "adu", "pbg", "pbm", "iui", "gqh", "emj", "pbw", "ius", "emq", "tke", "emv", "kzb", "aev"};
    private static final String[] s1 = {"kzd", "pcg", "tkm", "vor", "cjb", "rgo", "zwz", "tkt", "kzr", "pcu", "tlk", "rho", "ckc", "gsh", "vpy", "pds", "eol", "tmb", "ckp", "vqe", "vqh", "ckz", "zys", "zyy", "eph", "ahc", "tmw", "pes", "riy", "gto", "zzg", "gtu", "nbc", "nbi", "vrp", "nbj", "zzv", "iyf", "aib", "cmg", "aif", "iys", "cmq", "guu", "ain", "eqr", "rka", "eqv", "vsj", "pgd", "guz", "vsi", "vsk", "toi", "gva", "too", "cna", "erg", "ncr", "ajg", "erp", "izu", "rla", "cnq", "gvw", "cny", "tpn", "ese", "rlr", "tpy", "esj", "coh", "rly", "akk", "ndv", "ndy", "gwt", "akq", "rmf", "vuj", "vul", "coz", "lae", "vuo", "nej", "etb", "rmm", "rmp", "nek", "rms", "neu", "vvd", "lbg", "pjj", "trp", "lbh", "nfl", "rns", "trw", "cqg"};
    private static final String[] s2 = {"vvz", "lbt", "lbw", "lbv", "pjz", "cqr", "cqu", "pka", "pkd", "roe", "tsg", "amw", "ngd", "rog", "ngh", "pkj", "lce", "gzd", "gzh", "crf", "lcm", "vwz", "evi", "rou", "vwy", "crj", "gzo", "ani", "crm", "tta", "ngy", "gzy", "ttl", "ldg", "ewd", "plp", "nhp", "plw", "aok", "nhw", "csq", "ewu", "aoq", "csu", "pmb", "pmd", "vyi", "pme", "rqg", "tul", "nie", "vyo", "leg", "pmn", "rqq", "lep", "jam", "niu", "api", "apm", "vze", "ctt", "rrd", "jba", "apz", "jbe", "lfg", "tvn", "jbd", "njk", "njl", "eyg", "jbl", "jbp", "aqh", "jbs", "cuq", "aqp", "nkd", "nkg", "rsm", "lgg", "pok", "pom", "cvj", "yaa", "lgu", "yad", "rta", "ezu", "art", "yal", "lhf", "jdg", "yav", "jdn", "lhr", "cwl", "cwo", "cwr"};
    private static final String[] s3 = {"ruh", "tyj", "tyi", "typ", "rum", "jeh", "jeg", "hah", "lil", "jew", "haw", "prg", "rvh", "rvl", "jfk", "tzu", "cyh", "hbl", "cyk", "aui", "ljt", "jfu", "cyn", "ydh", "cys", "ydg", "cyu", "ydk", "psg", "noj", "jge", "rwo", "cza", "czd", "ydz", "rws", "noo", "avg", "psw", "lku", "jha", "npd", "hdb", "llj", "npr", "npt", "llr", "pty", "awl", "awq", "jhy", "wbe", "hdv", "aws", "awu", "nqb", "yfk", "ryg", "wbn", "wbm", "heb", "jic", "hea", "lmh", "yft", "ryt", "fah", "faj", "lms", "ygg", "jiz", "rzb", "pvf", "lne", "pvj", "jjf", "nrm", "jjk", "hfh", "ayb", "wcy", "lno", "fbi", "hfo", "ayh", "lnr", "yhb", "fbn", "wda", "hfv", "yhl", "pwc", "wdk", "lod", "pwh", "loh", "nsi", "pwp", "nsq", "hgk"};
    private static final String[] s4 = {"azj", "fcn", "fco", "fcr", "azn", "hgs", "yid", "yik", "fcz", "azv", "yiq", "ntf", "lpg", "uaq", "fdd", "ntl", "uau", "fdi", "lpn", "jlp", "hht", "wfj", "yjp", "nue", "wfo", "jmc", "hif", "lqj", "dab", "hie", "pym", "ubv", "dah", "dao", "his", "wge", "ykf", "wgf", "uci", "lra", "lre", "ucm", "pzi", "wgr", "pzo", "nvo", "jnj", "pzr", "jnq", "dbp", "ffr", "udh", "ylp", "whn", "hkc", "fga", "fgf", "nwp", "fgh", "nwq", "fgl", "hkp", "jot", "hks", "lsz", "wig", "hkz", "ymj", "sag", "hlc", "hlf", "ddd", "hlq", "ddm", "wjc", "ltz", "yng", "ufe", "hlz", "ynm", "ufk", "ufn", "hma", "sbk", "jqe", "ufs", "fie", "lun", "ufz", "bah", "jqo", "hmq", "fit", "hmu", "jqy", "yoj", "hmy", "nzb", "jrc", "yoq"};
    private static final String[] s5 = {"ugl", "jre", "nzh", "yow", "yov", "dfe", "ugu", "bbe", "dff", "bbi", "dfk", "nzv", "fjn", "ypc", "dfr", "hny", "bbr", "wli", "lwa", "dfz", "jsa", "hob", "jse", "ypv", "bcf", "jsn", "bce", "jso", "hop", "sed", "sef", "uio", "yqu", "uis", "yqv", "fll", "qaz", "wnd", "bdp", "dhy", "flz", "lyc", "yrr", "wnv", "hqh", "wnw", "wnz", "ujy", "beg", "bei", "fmp", "fmr", "ysd", "lyz", "sge", "ukf", "lzc", "bew", "lzb", "ysq", "won", "bey", "uko", "sgo", "lzm", "sgs", "dje", "sgv", "djh", "fnn", "jvu", "wpb", "jvt", "jvv", "ula", "djs", "wph", "fnv", "djt", "wpj", "ull", "wpn", "ytp", "shi", "wpo", "ytq", "fob", "ytv", "wpt", "jwj", "qdp", "wpv", "dke", "bgi", "jws", "yud", "sic", "sib", "dku", "wqk"};
    private static final String[] s6 = {"dky", "umj", "htb", "qep", "fpi", "umy", "htm", "fpj", "wrc", "dlr", "qfe", "dlz", "dmb", "fqh", "hui", "obr", "bin", "ska", "jyw", "bis", "biu", "ock", "frc", "dnc", "fre", "skp", "qgn", "hvf", "sku", "jzm", "skw", "qhb", "slc", "dnw", "upg", "odc", "wto", "upo", "slp", "bka", "doc", "dof", "fsg", "fsj", "upy", "bki", "dok", "wub", "sma", "wug", "bkq", "bkt", "smf", "smi", "wuo", "oek", "uqq", "smt", "blf", "uqz", "wvd", "wvf", "yzg", "ure", "wvk", "uri", "mbc", "hyb", "ofh", "urq", "mbk", "fug", "mbp", "urw", "snu", "qju", "mbt", "wwa", "mbu", "bmk", "dqp", "wwb", "hyv", "bmp", "wwd", "wwg", "wwi", "ush", "bmu", "soi", "mch", "drb", "ogo", "mcl", "ogn", "usw", "bne", "ogs", "bnm", "bnl"};
    private static final String[] s7 = {"fvp", "mcy", "hzt", "uta", "drs", "ute", "drt", "hzz", "utr", "boh", "fwp", "wye", "dsq", "qmg", "sqh", "mee", "uuo", "wyp", "kaf", "dtg", "bpe", "uuz", "oiv", "met", "mev", "dtn", "kay", "srf", "dtv", "uvn", "srl", "wzq", "mfg", "qnr", "mfm", "fyj", "kbt", "qnz", "dum", "ojw", "kbs", "bqn", "mfz", "fys", "bqt", "okb", "duw", "okd", "qop", "qow", "ssy", "okv", "kcu", "zad", "fzr", "zag", "qpa", "dvv", "sth", "stg", "olh", "oli", "qpn", "zaw", "bsg", "dwk", "sty", "olu", "dwp", "bso", "suc", "kdy", "dwv", "dwu", "bsv", "qqf", "qqh", "omf", "bsy", "sum", "kee", "omk", "mii", "zbt", "suq", "iaf", "mij", "omq", "qqw", "ian", "omv", "keu", "zce", "zcj", "zcl", "iaz", "qrg", "uzn", "zcq", "qrk"};
    private static final String[] s8 = {"ibc", "mji", "uzv", "bud", "mjn", "mjr", "uzz", "kfr", "qrw", "dyn", "dyr", "bup", "qsb", "buw", "swk", "icc", "qsl", "ici", "swt", "icl", "oou", "bvj", "dzr", "zeg", "qtb", "ope", "opr", "ops", "mlq", "opv", "zfa", "qtz", "idw", "bwu", "oqb", "qug", "kid", "zfu", "gab", "zfv", "kik", "kil", "syy", "oqt", "oqv", "gar", "iez", "qvg", "mnf", "mne", "zgq", "qvl", "zgv", "kjh", "szo", "kjj", "kjl", "byc", "szs", "bye", "ifp", "kjq", "bym", "qwe", "ose", "zho", "zhq", "mod", "kkb", "kkf", "zht", "qwo", "qwq", "bze", "igk", "bzj", "igs", "bzo", "kkw", "bzp", "vac", "van", "bzz", "mpj", "ihe", "mpi", "otm", "vau", "ihl", "vaz", "klo", "klr", "klq", "zjb", "klu", "vbb", "zjl", "zjo", "vbj", "zjr"};
    private static final String[] s9 = {"kmg", "zjt", "kmi", "eac", "vbr", "gei", "gej", "ouu", "eak", "gel", "geq", "ges", "vch", "gey", "kna", "vck", "zko", "knc", "knf", "mrh", "eba", "ovm", "ovr", "ovt", "ijn", "vda", "ebp", "knw", "knz", "kny", "ijz", "zlk", "vdi", "zlm", "gfy", "msc", "owd", "koc", "kob", "msi", "owm", "vdr", "owp", "kon", "msp", "kou", "msy", "zme", "msz", "zmi", "taf", "zmp", "zmo", "zmt", "veo", "ver", "edl", "vfb", "ghv", "zni", "ily", "edw", "tbi", "oyg", "imc", "tbn", "vft", "eed", "imm", "imn", "imp", "caj", "giq", "oyy", "vgb", "imu", "cao", "zoh", "zog", "vgj", "zos", "zou", "gjc", "kri", "gjh", "mvn", "krk", "tcz", "gjn", "krt", "efm", "gjs", "inu", "cbr", "vhg", "gjy", "vhk", "zps", "ioc", "ioe"};

    public static final String[] getStringTab(int digit){
        for(Field field: RandomConst.class.getDeclaredFields()){
            if((PREFIX + digit).equals(field.getName())){
                try {
                    return (String[]) field.get(null);
                } catch (IllegalAccessException e) {
                    return null;
                }
            }
        }
        return null;
    }

}