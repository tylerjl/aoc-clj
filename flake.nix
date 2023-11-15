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
          overlays = [ devshell.overlays.default ];
        };
      in

      {
        devShells.default = pkgs.devshell.mkShell {
          packages = with pkgs; [ clojure clojure-lsp leiningen ];
        };

        packages = rec {
          aoc-clj = clj-nix.lib.mkCljApp {
            inherit pkgs;
            modules = [{
              projectSrc = ./.;
              name = "me.tylerjl/aoc-clj";
              main-ns = "aoc-clj.core";
            }];
          };
          default = aoc-clj;
        };
      });
}
