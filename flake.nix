{
  description = "Advent of Code solutions in Clojure";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    clj-nix = {
      url = "github:jlesquembre/clj-nix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
    devshell = {
      url = "github:numtide/devshell";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };
  outputs = { self, nixpkgs, flake-utils, clj-nix, devshell }:

    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          overlays = [ devshell.overlay ];
        };
        cljpkgs = clj-nix.packages."${system}";
      in

      {
        devShells.default = pkgs.devshell.mkShell {
          packages = with pkgs; [ clojure clojure-lsp leiningen ];
        };

        packages = {

          aoc-clj = cljpkgs.mkCljBin {
            projectSrc = ./.;
            name = "me.tylerjl/aoc-clj";
            main-ns = "aoc-clj.core";
            jdkRunner = pkgs.jdk17_headless;
          };

          aoc-jdk = cljpkgs.customJdk {
            cljDrv = self.packages."${system}".aoc-clj;
            locales = "en";
          };

          aoc-graal = cljpkgs.mkGraalBin {
            cljDrv = self.packages."${system}".aoc-clj;
          };

        };
      });

}
