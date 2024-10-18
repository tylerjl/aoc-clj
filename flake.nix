{
  description = "Advent of Code solutions in Clojure";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-parts.url = "github:hercules-ci/flake-parts";
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

  outputs = inputs @ { clj-nix, flake-parts, flake-utils, devshell, ... }:
    flake-parts.lib.mkFlake { inherit inputs; } {
      imports = [
        devshell.flakeModule
      ];

      systems = flake-utils.lib.defaultSystems;

      perSystem = { pkgs, system, ... }: {
        _module.args.pkgs = import inputs.nixpkgs {
          inherit system;
          overlays = [
            clj-nix.overlays.default
          ];
        };
        devshells.default = {
          packages = with pkgs; [
            clojure
            clojure-lsp
            deps-lock
            leiningen
          ];
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
      };
    };
}
