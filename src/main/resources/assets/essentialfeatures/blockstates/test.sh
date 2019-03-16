declare -a names=("white" "orange" "magenta" "light_blue" "yellow" "lime" "pink" "gray" "silver" "cyan" "purple" "blue" "brown" "green" "red" "black")

for i in "${names[@]}"
do
    cat << EOF > "lit_${i}_stained_redstone_torch.json"
    {
      "forge_marker": 1,
      "defaults": {
        "model": "lit_redstone_torch",
        "textures": {
          "torch": "essentialfeatures:blocks/lit_${i}_stained_redstone_torch"
          }
      },
      "variants": {
        "inventory": [{}],
        "facing": {
          "up": {},
          "east": {
            "model": "lit_redstone_torch_wall"
        },
          "south": {
            "model": "lit_redstone_torch_wall",
            "y": 90
        },
          "west": {
            "model": "lit_redstone_torch_wall",
            "y": 180
        },
          "north": {
            "model": "lit_redstone_torch_wall",
            "y": 270
        }
    }
      }
    } 
EOF

done
