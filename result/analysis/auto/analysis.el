(TeX-add-style-hook
 "analysis"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "11pt")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("footmisc" "bottom") ("hyperref" "hidelinks")))
   (TeX-run-style-hooks
    "latex2e"
    "article"
    "art11"
    "footmisc"
    "courier"
    "graphicx"
    "hyperref"
    "amsmath"
    "amsfonts"
    "amssymb"
    "geometry"
    "bm"
    "enumerate"))
 :latex)

