class HelloWorld {

    static class ParseException extends Exception {
        protected ParseException(String errorMessage) {
            super(errorMessage);
        }
    }

    static class CommandLine {
        private Options opts;
        public CommandLine() {
            this.opts = new Options();
        }
        protected boolean hasOption(String opt) {
            for (Option o : opts.options) {
                if (o == null) {
                    continue;
                }
                if (o.longopt.equals(opt) || o.shortopt.equals(opt)) {
                    return true;
                }
            }
            return false;
        }
        protected String getOptionValue(String opt) {

            return "";
        }
    }
    static class CommandLineParser {
        protected CommandLine parse(Options options, String[] args) throws ParseException {
            return new CommandLine();
        }
    }
    static class DefaultParser extends CommandLineParser {

    }
    static class Options {
        private Option[] options;
        public Options() {
            this.options = new Option[100];
        }
        protected void addOption(String shortopt, String longopt, boolean has_arg, String desc) {
        }
        protected void addOption(Option option) {

        }
    }
    static class Option {
        private String shortopt;
        private String longopt;
        private boolean has_arg;
        private String desc;
        public Option(String shortopt, String longopt, boolean has_arg, String desc) {
            this.shortopt = shortopt;
            this.longopt = longopt;
            this.has_arg = has_arg;
            this.desc = desc;
        }
        static class OptionBuilder {
            private String name;
            private Option opt;
            public OptionBuilder(String optname) {
                this.opt = new Option(null, null, false, null);
                this.name = optname;
            }
            public OptionBuilder(String optname, String shortopt, String longopt, boolean has_arg, String desc) {
                this.name = optname;
                this.opt = new Option(shortopt, longopt, has_arg, desc);
            }
            protected OptionBuilder longOpt(String longopt) {
                this.opt.longopt = longopt;
                return this;
            }
            protected OptionBuilder desc(String desc) {
                this.opt.desc = desc;
                return this;
            }
            protected OptionBuilder hasArg() {
                this.opt.has_arg = true;
                return this;
            }
            protected Option build() {
                return new Option(this.opt.shortopt, this.opt.longopt, this.opt.has_arg, this.opt.desc);
            }
        }
        static protected OptionBuilder builder(String argname) {
            return new OptionBuilder(argname);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        for (String arg : args) {
            System.out.println(arg);
        }
        // create the command line parser
        CommandLineParser parser = new DefaultParser();

        // create the Options
        Options options = new Options();
        options.addOption("a", "all", false, "do not hide entries starting with .");
        options.addOption(Option.builder("SIZE").longOpt("block-size")
                                        .desc("use SIZE-byte blocks")
                                        .hasArg()
                                        .build());

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);

            // validate that block-size has been set
            if (line.hasOption("block-size")) {
                // print the value of block-size
                System.out.println(line.getOptionValue("block-size"));
            }
        }
        catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }
    }
}
